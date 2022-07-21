package com.lmf.api.intern.clans;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClanLeaderboard {

    private String clanTag;

    private String clanName;

    private Double averagePoints;

    private int remainingDecks;

    private int rankLocal;
}
