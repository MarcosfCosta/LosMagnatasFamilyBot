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
package com.lmf.api.intern.tournaments;

import java.util.Map;

import com.lmf.common.PaginationRequest;
import com.lmf.common.PaginationRequest;
import lombok.Builder;

public class TournamentsRequest extends PaginationRequest {

  private final String name;

  @Builder
  private TournamentsRequest(int limit, String after, String before, boolean storeRawResponse, String name) {
    super(limit, after, before, storeRawResponse);
    this.name = name;
  }

  public static TournamentsRequestBuilder builder() {
    return new TournamentsRequestBuilder();
  }

  @Override
  public Map<String, Object> getRestParameters() {
    Map<String, Object> map = super.getRestParameters();
    return map;
  }

  @Override
  public Map<String, Object> getQueryParameters() {
    Map<String, Object> map = super.getQueryParameters();
    map.put("name", name);
    return map;
  }

}