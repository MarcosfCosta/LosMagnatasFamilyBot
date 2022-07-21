package com.lmf.service.cache;

import com.google.common.cache.LoadingCache;
import com.google.common.graph.Graph;
import com.google.common.util.concurrent.Futures;
import com.lmf.ClashRoyaleServiceConnector;
import com.lmf.api.intern.clans.ClanApi;
import com.lmf.api.intern.clans.ClanLeaderboard;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.lmf.api.intern.clans.currentriverrace.CurrentRiverRaceRequest;
import com.lmf.api.intern.clans.currentriverrace.CurrentRiverRaceResponse;
import com.lmf.api.intern.locations.LocationApi;
import com.lmf.api.intern.locations.rankings.clanwar.ClanWarRankingsRequest;
import com.lmf.api.intern.locations.rankings.clanwar.ClanWarRankingsResponse;
import com.lmf.config.ClashRoyaleServiceConfig;
import com.lmf.connector.StandardConnector;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@NoArgsConstructor
@Data
public class ClanLeaderboardCacheImpl implements ClanLeaderboardCache {

    private LoadingCache<String, List<ClanLeaderboard>> cache;

    @Autowired
    private ClashRoyaleServiceConfig clashRoyaleServiceConfig;

    private static ExecutorService executorService = Executors.newFixedThreadPool(3);


    @Override
    public void load() {

        cache = CacheBuilder.newBuilder()
                            .maximumSize(5)
                            .refreshAfterWrite(2, TimeUnit.MINUTES)
                            .build(
                                    new CacheLoader<String, List<ClanLeaderboard>>() {

                                        public List<ClanLeaderboard> load(String key) { // no checked exception
                                            try {
                                                ClashRoyaleServiceConnector crApiConnector= crApiConnector = new ClashRoyaleServiceConnector(clashRoyaleServiceConfig.getBaseUrl(),
                                                        clashRoyaleServiceConfig.getApiKey(), new StandardConnector());

                                                LocationApi locationApi = crApiConnector.getApi(LocationApi.class);
                                                ClanApi clanApi = crApiConnector.getApi(ClanApi.class);


                                                ClanWarRankingsResponse clanWarRankingsResponse = locationApi.getClanWarRankings(
                                                        ClanWarRankingsRequest.builder(57000188)
                                                                .limit(30)
                                                                .storeRawResponse(true)
                                                                .build()).get();

                                                Map<String, ClanLeaderboard> listTagTopClanWars2 = new HashedMap<>();


                                                clanWarRankingsResponse.getItems().forEach(x ->
                                                        {
                                                            try {
                                                                CurrentRiverRaceResponse currentRiverRaceResponse = clanApi.getCurrentRiverRace(CurrentRiverRaceRequest.builder(x.getTag())
                                                                        .storeRawResponse(true).build()).get();
                                                                AtomicInteger numDecksUsedToday = new AtomicInteger(0);


                                                                currentRiverRaceResponse.getClan().getParticipants()
                                                                                        .forEach(u -> {
                                                                                            numDecksUsedToday.addAndGet(u.getDecksUsedToday());
                                                                                        });


                                                                if (numDecksUsedToday.intValue() != 0) {
                                                                    listTagTopClanWars2.put(currentRiverRaceResponse.getClan()
                                                                                                                    .getName(),
                                                                            //1.0 avoid losing precision (lose decimal places)

                                                                            ClanLeaderboard.builder()
                                                                                    .clanName(currentRiverRaceResponse.getClan()
                                                                                                                      .getName())
                                                                                    .clanTag(currentRiverRaceResponse.getClan()
                                                                                                                     .getTag())
                                                                                    .averagePoints(new BigDecimal(1.0 * currentRiverRaceResponse.getClan()
                                                                                                                                                .getPeriodPoints() / numDecksUsedToday.intValue())
                                                                                            .setScale(2, RoundingMode.HALF_UP)
                                                                                            .doubleValue())
                                                                                    .remainingDecks(200 - numDecksUsedToday.intValue())
                                                                                    .rankLocal(x.getRank())
                                                                                    .build());
                                                                }

                                                            } catch (InterruptedException | ExecutionException e) {
                                                                log.error(MessageFormat.format("Error while getting current river race info for the local leaderboard command {}", e));
                                                            }
                                                        }
                                                );

                                                Map<String, ClanLeaderboard> localRankSorted = new LinkedHashMap<>();

                                                listTagTopClanWars2.entrySet().stream()
                                                                   .collect(Collectors.toMap(k -> (String) k.getKey(), e -> ((ClanLeaderboard) e.getValue())))
                                                                   .entrySet()
                                                                   .stream()
                                                                   .sorted((e1, e2) -> (e2.getValue()
                                                                                          .getAveragePoints()
                                                                                          .compareTo(e1.getValue()
                                                                                                       .getAveragePoints())))
                                                                   .forEachOrdered(x -> localRankSorted.put(x.getKey(), x.getValue()));

                                                return (List) localRankSorted.values().stream().collect(Collectors.toCollection(ArrayList::new));
                                            } catch (Exception e) {
                                                log.error("");
                                            }
                                            return new ArrayList<ClanLeaderboard>();
                                        }
                                    });



        try {

            cache.get(CacheConfig.CLAN_LEADERBOARD_CACHE);

        } catch (Exception e) {
            log.error("error ", e);
        }
    }

    @Override
    public Optional<ClanLeaderboard> get(String userId) {
        return Optional.empty();
    }
}
