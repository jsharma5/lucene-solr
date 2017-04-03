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
package org.apache.solr.core;

import java.util.Map;
import java.util.Set;

import com.codahale.metrics.MetricRegistry;
import org.apache.solr.util.stats.MetricUtils;

/**
 * Interface for getting various ui friendly strings
 * for use by objects which are 'pluggable' to make server administration
 * easier.
 */
public interface SolrInfoBean {

  /**
   * Category of Solr component.
   */
  enum Category { CONTAINER, ADMIN, CORE, QUERY, UPDATE, CACHE, HIGHLIGHTER, QUERYPARSER, SPELLCHECKER,
    SEARCHER, REPLICATION, TLOG, INDEX, DIRECTORY, HTTP, OTHER }

  /**
   * Top-level group of beans or metrics for a subsystem.
   */
  enum Group { jvm, jetty, node, core, collection, shard, cluster, overseer }

  /**
   * Simple common usage name, e.g. BasicQueryHandler,
   * or fully qualified class name.
   */
  String getName();
  /** Simple one or two line description */
  String getDescription();
  /** Category of this component */
  Category getCategory();

  /** Optionally return metrics that this component reports, or null. */
  default Map<String, Object> getMetrics() {
    if (getMetricRegistry() == null || getMetricNames() == null) {
      return null;
    }
    return MetricUtils.convertMetrics(getMetricRegistry(), getMetricNames());
  }

  /**
   * Modifiable set of metric names that this component reports, or null if none.
   */
  Set<String> getMetricNames();

  /**
   * An instance of {@link MetricRegistry} that this component uses for keeping metrics, or null.
   */
  default MetricRegistry getMetricRegistry() {
    return null;
  }

  /** Register a metric name that this component reports. */
  default void registerMetricName(String name) {
    Set<String> names = getMetricNames();
    if (names != null) {
      names.add(name);
    }
  }
}
