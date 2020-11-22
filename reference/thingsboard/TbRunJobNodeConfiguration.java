/**
 * Copyright © 2016-2020 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thingsboard.rule.engine.scheduler;

import lombok.Data;
import org.thingsboard.rule.engine.api.NodeConfiguration;

import java.util.Collections;
import java.util.Map;

@Data
public class TbRunJobNodeConfiguration implements NodeConfiguration<TbRunJobNodeConfiguration> {

//    private String serverAddress;
//    private String appName;
//    private String appPwd;
//    private Map<String, String> clientProperties;


    private String restEndpointUrlPattern;
    private String requestMethod;
    private Map<String, String> headers;
    private boolean useSimpleClientHttpFactory;
    private int readTimeoutMs;
    private int maxParallelRequestsCount;
    private boolean useRedisQueueForMsgPersistence;
    private boolean trimQueue;
    private int maxQueueSize;

    @Override
    public TbRunJobNodeConfiguration defaultConfiguration() {
        TbRunJobNodeConfiguration configuration = new TbRunJobNodeConfiguration();

        configuration.setRestEndpointUrlPattern("http://localhost/api");
        configuration.setRequestMethod("POST");
        configuration.setHeaders(Collections.emptyMap());
        configuration.setUseSimpleClientHttpFactory(false);
        configuration.setReadTimeoutMs(0);
        configuration.setMaxParallelRequestsCount(0);
        configuration.setUseRedisQueueForMsgPersistence(false);
        configuration.setTrimQueue(false);

        return configuration;
    }
}
