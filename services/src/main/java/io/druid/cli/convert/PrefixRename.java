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

package io.druid.cli.convert;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 */
public class PrefixRename implements PropertyConverter
{
  private final String prefix;
  private final String outputPrefix;

  private final AtomicBoolean ran = new AtomicBoolean(false);

  public PrefixRename(
      String prefix,
      String outputPrefix
  )
  {
    this.prefix = prefix;
    this.outputPrefix = outputPrefix;
  }

  @Override
  public boolean canHandle(String property)
  {
    return property.startsWith(prefix) && !ran.get();
  }

  @Override
  public Map<String, String> convert(Properties properties)
  {
    if (!ran.getAndSet(true)) {
      Map<String, String> retVal = Maps.newLinkedHashMap();

      for (String property : properties.stringPropertyNames()) {
        if (property.startsWith(prefix)) {
          retVal.put(property.replace(prefix, outputPrefix), properties.getProperty(property));
        }
      }

      return retVal;
    }
    return ImmutableMap.of();
  }
}
