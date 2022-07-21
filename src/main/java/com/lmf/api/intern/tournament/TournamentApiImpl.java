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
package com.lmf.api.intern.tournament;

import java.util.concurrent.Future;

import com.lmf.api.ApiContext;
import com.lmf.api.BaseApi;
import com.lmf.api.intern.tournaments.TournamentsRequest;
import com.lmf.api.intern.tournaments.TournamentsResponse;
import com.lmf.api.intern.tournaments.info.TournamentRequest;
import com.lmf.api.intern.tournaments.info.TournamentResponse;
import com.lmf.api.ApiContext;
import com.lmf.api.BaseApi;

import com.lmf.api.intern.tournaments.TournamentsResponse;
import com.lmf.api.intern.tournaments.TournamentsRequest;
import com.lmf.api.intern.tournaments.info.TournamentResponse;
import com.lmf.api.intern.tournaments.info.TournamentRequest;

class TournamentApiImpl extends BaseApi implements TournamentApi {

  TournamentApiImpl(ApiContext apiContext) {
    super(apiContext);
  }

  @Override
  public Future<TournamentsResponse> findAll(TournamentsRequest tournamentsRequest) {
    return get("/tournaments", tournamentsRequest, TournamentsResponse.class);
  }

  public Future<TournamentResponse> findByTag(TournamentRequest tournamentRequest) {
    return get("/tournaments/{tournamentTag}", tournamentRequest, TournamentResponse.class);
  }

}