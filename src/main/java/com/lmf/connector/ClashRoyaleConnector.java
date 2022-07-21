//package com.lmf.connector;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.lmf.ClashRoyaleServiceConnector;
//import com.lmf.api.intern.cards.CardApi;
//import com.lmf.api.intern.challenges.ChallengeApi;
//import com.lmf.api.intern.clans.ClanApi;
//import com.lmf.api.intern.globaltournaments.GlobalTournamentApi;
//import com.lmf.api.intern.locations.LocationApi;
//import com.lmf.api.intern.players.PlayerApi;
//import com.lmf.api.intern.tournament.TournamentApi;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ClashRoyaleConnector {
//
//    @Autowired
//    private com.lmf.ClashRoyaleServiceConnector crApiConnector;
//
//    private static ClanApi clanApi;
//
//    private static PlayerApi playerApi;
//
//    private static CardApi cardApi;
//
//    private static TournamentApi tournamentApi;
//
//    private static LocationApi locationApi;
//
//    private static ChallengeApi challengeApi;
//
//    private static GlobalTournamentApi globalTournamentApi;
//
//
//    public void initialize() {
//
//        crApiConnector.createApiConnector("https://api.clashroyale.com/v1/", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6ImQ0ZWY5YjY0LWZjZTAtNDE5MS1hOThmLWI0ZWZlZWNhYTJkOCIsImlhdCI6MTY1NTY3MzIwNiwic3ViIjoiZGV2ZWxvcGVyL2Y0MzVjNWNkLWZjMjEtOTExYS1kNGQzLTZmMDRkNGQzYzU1OCIsInNjb3BlcyI6WyJyb3lhbGUiXSwibGltaXRzIjpbeyJ0aWVyIjoiZGV2ZWxvcGVyL3NpbHZlciIsInR5cGUiOiJ0aHJvdHRsaW5nIn0seyJjaWRycyI6WyI5NC4xMzIuMjI3LjM3Il0sInR5cGUiOiJjbGllbnQifV19._x-TzN79P7W0TAwpTLLbO48_909oxUGdxrm8oXT3bBmiE5-yJ6aUa-JiP0xqXhil18JWZTD4Cz9DiPfJqqzH3A\n",
//                new StandardConnector());
//
//        clanApi = crApiConnector.getApi(ClanApi.class);
//        playerApi = crApiConnector.getApi(PlayerApi.class);
//        cardApi = crApiConnector.getApi(CardApi.class);
//        tournamentApi = crApiConnector.getApi(TournamentApi.class);
//        locationApi = crApiConnector.getApi(LocationApi.class);
//        challengeApi = crApiConnector.getApi(ChallengeApi.class);
//        globalTournamentApi = crApiConnector.getApi(GlobalTournamentApi.class);
//    }
//}
