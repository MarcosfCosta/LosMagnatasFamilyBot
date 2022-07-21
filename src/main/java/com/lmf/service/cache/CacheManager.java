package com.lmf.service.cache;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component("BotCacheManager")
@Slf4j
@Data
public class CacheManager {

    @Autowired
    private ClanLeaderboardCache clanLeaderboardCache;

    @PostConstruct
    public void load(){
        final List<BaseCache> cacheList = Arrays.asList(clanLeaderboardCache);
        cacheList.forEach(BaseCache::load);
    }
}
