/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.druid.query.lookup;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.google.common.base.Preconditions;
import io.druid.query.extraction.ExtractionFn;

import javax.annotation.Nullable;

/**
 * This class allows the usage of a Druid Registered Cached Lookup.
 * @link http://druid.io/docs/latest/querying/lookups.html
 */
@JsonTypeName("registeredLookup")
public class RegisteredCachedLookupExtractor implements ExtractionFn
{
  @JsonProperty("lookup")
  private final String lookup;
  @JsonProperty("retainMissingValue")
  private final boolean retainMissingValue = true;
  @JsonProperty("injective")
  private final Boolean injective = false;

  @JsonCreator
  public RegisteredCachedLookupExtractor(@JsonProperty("lookup") String lookup)
  {
    Preconditions.checkArgument(lookup != null, "`lookup` required");
    this.lookup = lookup;
  }

  @Nullable
  @Override
  public String apply(@Nullable Object value)
  {
    return null;
  }

  @Nullable
  @Override
  public String apply(@Nullable String value)
  {
    return null;
  }

  @Override
  public String apply(long value)
  {
    return null;
  }

  @Override
  public boolean preservesOrdering()
  {
    return false;
  }

  @Override
  public ExtractionType getExtractionType()
  {
    return ExtractionType.MANY_TO_ONE;
  }

  @Override
  public byte[] getCacheKey()
  {
    return new byte[0];
  }

  public String getLookup()
  {
    return lookup;
  }

  public boolean isRetainMissingValue()
  {
    return retainMissingValue;
  }

  public Boolean getInjective()
  {
    return injective;
  }

}
