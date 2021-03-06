/**
 * Copyright 2016 StreamSets Inc.
 *
 * Licensed under the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.streamsets.pipeline.stage.destination.datalake;

import com.streamsets.pipeline.api.Stage;
import com.streamsets.pipeline.sdk.TargetRunner;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestDataLakeTarget {
  @Test
  public void testInvalidHostName() throws Exception {
    DataLakeTarget target = new DataLakeTargetBuilder()
        .accountFQDN("dummy:9000")
        .authTokenEndpoint("dummy:9500")
        .build();

    TargetRunner targetRunner = new TargetRunner.Builder(DataLakeDTarget.class, target)
        .build();

    List<Stage.ConfigIssue> issues = targetRunner.runValidateConfigs();

    assertEquals(1, issues.size());

  }

  @Test
  public void testTargetDirectory() throws Exception {
    final String directoryPath = "/tmp/out/2016-11-29";
    final String prefix = "sdc-id";

    DataLakeTarget target = new DataLakeTarget(new DataLakeConfigBean());
    final String filePath = target.getFilePath(directoryPath, prefix);
    Assert.assertTrue(filePath.startsWith(directoryPath + "/" + prefix));
  }
}
