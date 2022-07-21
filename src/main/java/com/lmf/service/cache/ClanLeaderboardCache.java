package com.lmf.service.cache;

import com.lmf.api.intern.clans.ClanLeaderboard;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface ClanLeaderboardCache extends BaseCache {

    Optional<ClanLeaderboard> get(String userId);

}
