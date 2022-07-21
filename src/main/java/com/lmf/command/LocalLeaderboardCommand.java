package com.lmf.command;

import com.lmf.ClashRoyaleServiceConnector;
import com.lmf.api.intern.clans.ClanApi;
import com.lmf.api.intern.clans.ClanLeaderboard;
import com.lmf.api.intern.clans.currentriverrace.CurrentRiverRaceRequest;
import com.lmf.api.intern.clans.currentriverrace.CurrentRiverRaceResponse;
import com.lmf.api.intern.locations.LocationApi;
import com.lmf.api.intern.locations.rankings.clanwar.ClanWarRankingsRequest;
import com.lmf.api.intern.locations.rankings.clanwar.ClanWarRankingsResponse;
import com.lmf.config.ClashRoyaleServiceConfig;
import com.lmf.connector.StandardConnector;
import com.lmf.service.cache.CacheConfig;
import com.lmf.service.cache.CacheManager;
import com.lmf.service.cache.ClanLeaderboardCacheImpl;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

@Slf4j
@Component
public class LocalLeaderboardCommand implements BotCommand {

    @Autowired
    private ClashRoyaleServiceConfig clashRoyaleServiceConfig;

    @Autowired
    private CacheManager cacheManager;

    public void execute(SlashCommandInteractionEvent event) {

        try {

            log.info("Localleaderboard command run in...");

            List<ClanLeaderboard> localRankSorted = new ArrayList<>();


            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(new Color(252, 36, 124));
            embed.setTitle("__Daily War Leaderboard__");
            embed.addField("Location:", "Portugal \uD83C\uDDF5\uD83C\uDDF9", false);
            embed.addField("League:", "All", false);
            embed.setThumbnail(event.getGuild().getIconUrl());

            localRankSorted = ((ClanLeaderboardCacheImpl) cacheManager.getClanLeaderboardCache()).getCache().get(CacheConfig.CLAN_LEADERBOARD_CACHE);

            AtomicInteger positionRank = new AtomicInteger(1);
            localRankSorted
                   .forEach(x -> {
                       if( positionRank.intValue() == 10){
                           return;
                       }
                       embed.addField(positionRank.intValue() + ". " + x.getClanName(),
                               " \u2694" + x.getAveragePoints()
                               + " \u231A" + x.getRemainingDecks() + " \uD83C\uDDF5\uD83C\uDDF9 #" + x.getRankLocal(),  false);
                       positionRank.incrementAndGet();
                   });

            event.getChannel().sendMessageEmbeds(embed.build()).queue();

        } catch (Exception e) {
            log.error(MessageFormat.format("Error while executing local leaderboard command {}", e));
        }
    }
}
