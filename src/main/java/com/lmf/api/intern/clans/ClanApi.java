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
package com.lmf.api.intern.clans;

import java.util.concurrent.Future;

import com.lmf.api.Api;
import com.lmf.api.Api;

import com.lmf.api.intern.clans.info.ClanResponse;
import com.lmf.api.intern.clans.info.ClanRequest;
import com.lmf.api.intern.clans.riverracelog.RiverRaceLogResponse;
import com.lmf.api.intern.clans.riverracelog.RiverRaceLogRequest;
import com.lmf.api.intern.clans.members.ClanMembersResponse;
import com.lmf.api.intern.clans.members.ClanMembersRequest;
import com.lmf.api.intern.clans.currentriverrace.CurrentRiverRaceResponse;
import com.lmf.api.intern.clans.currentriverrace.CurrentRiverRaceRequest;

public interface ClanApi extends Api {

  Future<ClansResponse> findAll(ClansRequest clansRequest);
  Future<ClanResponse> findByTag(ClanRequest clanRequest);
  Future<RiverRaceLogResponse> getRiverRaceLog(RiverRaceLogRequest riverRaceLogRequest);
  Future<ClanMembersResponse> getMembers(ClanMembersRequest clanMembersRequest);
  Future<CurrentRiverRaceResponse> getCurrentRiverRace(CurrentRiverRaceRequest currentRiverRaceRequest);

}
