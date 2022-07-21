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
package com.lmf.api.intern;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.lmf.api.Api;
import com.lmf.api.Api;
import com.lmf.api.intern.clans.ClanApi;
import com.lmf.api.intern.players.PlayerApi;
import com.lmf.api.intern.cards.CardApi;
import com.lmf.api.intern.tournament.TournamentApi;
import com.lmf.api.intern.locations.LocationApi;
import com.lmf.api.intern.challenges.ChallengeApi;
import com.lmf.api.intern.globaltournaments.GlobalTournamentApi;

public class DefaultApiClasses {

  private final Map<Class<? extends Api>, String> apiClassMap = Collections.unmodifiableMap(new HashMap<Class<? extends Api>, String>(){{
    put(ClanApi.class, "com.lmf.api.intern.clans.ClanApiImpl");
    put(PlayerApi.class, "com.lmf.api.intern.players.PlayerApiImpl");
    put(CardApi.class, "com.lmf.api.intern.cards.CardApiImpl");
    put(TournamentApi.class, "com.lmf.api.intern.tournament.TournamentApiImpl");
    put(LocationApi.class, "com.lmf.api.intern.locations.LocationApiImpl");
    put(ChallengeApi.class, "com.lmf.api.intern.challenges.ChallengeApiImpl");
    put(GlobalTournamentApi.class, "com.lmf.api.intern.globaltournaments.GlobalTournamentApiImpl");
  }});

  public Map<Class<? extends Api>, String> getApiClassMap() {
    return apiClassMap;
  }

}