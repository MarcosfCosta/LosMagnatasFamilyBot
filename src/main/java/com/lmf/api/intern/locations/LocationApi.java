/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lmf.api.intern.locations;

import java.util.concurrent.Future;

import com.lmf.api.Api;
import com.lmf.api.intern.locations.seasons.global.rankings.TopPlayerLeagueSeasonRankingsResponse;
import com.lmf.api.Api;

import com.lmf.api.intern.locations.info.LocationResponse;
import com.lmf.api.intern.locations.info.LocationRequest;
import com.lmf.api.intern.locations.rankings.clan.ClanRankingsResponse;
import com.lmf.api.intern.locations.rankings.clan.ClanRankingsRequest;
import com.lmf.api.intern.locations.rankings.player.PlayerRankingsResponse;
import com.lmf.api.intern.locations.rankings.player.PlayerRankingsRequest;
import com.lmf.api.intern.locations.rankings.clanwar.ClanWarRankingsResponse;
import com.lmf.api.intern.locations.rankings.clanwar.ClanWarRankingsRequest;
import com.lmf.api.intern.locations.seasons.global.TopPlayerLeagueSeasonsResponse;
import com.lmf.api.intern.locations.seasons.global.TopPlayerLeagueSeasonsRequest;
import com.lmf.api.intern.locations.seasons.global.info.TopPlayerLeagueSeasonResponse;
import com.lmf.api.intern.locations.seasons.global.info.TopPlayerLeagueSeasonRequest;
import com.lmf.api.intern.locations.seasons.global.rankings.TopPlayerLeagueSeasonRankingsResponse;
import com.lmf.api.intern.locations.seasons.global.rankings.TopPlayerLeagueSeasonRankingsRequest;

public interface LocationApi extends Api {

  Future<LocationsResponse> findAll(LocationsRequest locationsRequest);
  Future<LocationResponse> findById(LocationRequest locationRequest);
  Future<ClanRankingsResponse> getClanRankings(ClanRankingsRequest clanRankingsRequest);
  Future<PlayerRankingsResponse> getPlayerRankings(PlayerRankingsRequest playerRankingsRequest);
  Future<ClanWarRankingsResponse> getClanWarRankings(ClanWarRankingsRequest clanWarRankingsRequest);
  Future<TopPlayerLeagueSeasonsResponse> getTopPlayerLeagueSeasons(TopPlayerLeagueSeasonsRequest topPlayerLeagueSeasonsRequest);
  Future<TopPlayerLeagueSeasonResponse> getTopPlayerLeagueSeason(TopPlayerLeagueSeasonRequest topPlayerLeagueSeasonRequest);
  Future<TopPlayerLeagueSeasonRankingsResponse> getTopPlayerLeagueSeasonRankings(TopPlayerLeagueSeasonRankingsRequest topPlayerLeagueSeasonRankingsRequest);

}
