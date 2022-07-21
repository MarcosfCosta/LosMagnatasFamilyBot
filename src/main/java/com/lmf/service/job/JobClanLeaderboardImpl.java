package com.lmf.service.job;

import com.lmf.ClashRoyaleServiceConnector;
import com.lmf.api.intern.clans.ClanApi;
import com.lmf.api.intern.clans.ClanLeaderboard;
import com.lmf.api.intern.clans.currentriverrace.CurrentRiverRaceRequest;
import com.lmf.api.intern.clans.currentriverrace.CurrentRiverRaceResponse;
import com.lmf.api.intern.locations.LocationApi;
import com.lmf.api.intern.locations.rankings.clanwar.ClanWarRankingsRequest;
import com.lmf.api.intern.locations.rankings.clanwar.ClanWarRankingsResponse;
import com.lmf.config.BotConfig;
import com.lmf.config.ClashRoyaleServiceConfig;
import com.lmf.connector.StandardConnector;
import com.lmf.service.cache.CacheConfig;
import com.lmf.service.cache.CacheManager;
import com.lmf.service.cache.ClanLeaderboardCacheImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Component
@EnableAsync
public class JobClanLeaderboardImpl implements BaseJob{

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private ClashRoyaleServiceConfig clashRoyaleServiceConfig;

    @Autowired
    private BotConfig botConfig;

    //TODO Put it configurable
    @Scheduled(initialDelay = 60000,
            fixedRateString = "${lmfbot.job.clanleaderboard.interval}")
    public void execute() {

        try {
            log.info("Starting reloading clan leaderboard job");

            ClashRoyaleServiceConnector crApiConnector = new ClashRoyaleServiceConnector(clashRoyaleServiceConfig.getBaseUrl(),
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


            ((ClanLeaderboardCacheImpl) cacheManager.getClanLeaderboardCache()).getCache()
                                                                               .put(CacheConfig.CLAN_LEADERBOARD_CACHE, (List) localRankSorted.values()
                                                                                                                                              .stream()
                                                                                                                                              .collect(Collectors.toCollection(ArrayList::new)));

            log.info("Finished reload clan leaderboard job");


        } catch (Exception e) {
            log.error("Error reloading clan leaderboard job", e);
        }
    }
}
