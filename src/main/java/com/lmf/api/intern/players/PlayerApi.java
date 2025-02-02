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
package com.lmf.api.intern.players;

import java.util.concurrent.Future;

import com.lmf.api.Api;
import com.lmf.api.intern.players.upcomingchests.UpcomingChestsRequest;
import com.lmf.api.Api;

import com.lmf.api.intern.players.info.PlayerResponse;
import com.lmf.api.intern.players.info.PlayerRequest;
import com.lmf.api.intern.players.upcomingchests.UpcomingChestsResponse;
import com.lmf.api.intern.players.upcomingchests.UpcomingChestsRequest;
import com.lmf.api.intern.players.battlelog.BattleLogResponse;
import com.lmf.api.intern.players.battlelog.BattleLogRequest;

public interface PlayerApi extends Api {

  Future<PlayerResponse> findByTag(PlayerRequest playerRequest);
  Future<UpcomingChestsResponse> getUpcomingChests(UpcomingChestsRequest upcomingChestsRequest);
  Future<BattleLogResponse> getBattleLog(BattleLogRequest battleLogRequest);

}
