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
package org.apache.solr.client.solrj.cloud.autoscaling;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.solr.common.cloud.ClusterState;

/**
 *
 */
public class DelegatingClusterDataProvider implements ClusterDataProvider {
  protected ClusterDataProvider delegate;

  public DelegatingClusterDataProvider(ClusterDataProvider delegate) {
    this.delegate = delegate;
  }

  @Override
  public Map<String, Object> getNodeValues(String node, Collection<String> tags) {
    return delegate.getNodeValues(node, tags);
  }

  @Override
  public Map<String, Map<String, List<ReplicaInfo>>> getReplicaInfo(String node, Collection<String> keys) {
    return delegate.getReplicaInfo(node, keys);
  }

  @Override
  public Collection<String> getLiveNodes() {
    return delegate.getLiveNodes();
  }

  @Override
  public Map<String, Object> getClusterProperties() {
    return delegate.getClusterProperties();
  }

  @Override
  public ClusterState getClusterState() throws IOException {
    return delegate.getClusterState();
  }

  @Override
  public String getPolicyNameByCollection(String coll) {
    return delegate.getPolicyNameByCollection(coll);
  }

}
