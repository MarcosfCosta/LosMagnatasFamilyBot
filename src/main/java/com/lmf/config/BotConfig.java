package com.lmf.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@ComponentScan({"com.lmf.config"})
@PropertySource({"classpath:lmfbot-default.properties"})
public class BotConfig {

    @Value("${lmfbot.cache.localCache:tmp/lmfbotcache.cache}")
    public String localCache;

    @Value("${lmfbot.bot.token:OTk2ODg2NTI2OTE3MDg3NDE0.GqC7sp.TK1TtxTq1SdTyWlztq51S7_sPG_nzkQXm4aNiE}")
    public String botToken;

    @Value("${lmfbot.job.clanleaderboard.interval:3600000}")
    public String intervalJobClanLeaderboard;
}
