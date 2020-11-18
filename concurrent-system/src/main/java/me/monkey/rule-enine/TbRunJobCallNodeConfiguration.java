
package com.hachismart.rule.engine.scheduler;

import com.hachismart.rule.engine.api.NodeConfiguration;
import lombok.Data;

import java.util.Collections;
import java.util.Map;

@Data
public class TbRunJobCallNodeConfiguration implements NodeConfiguration<TbRunJobCallNodeConfiguration> {

    private String serverAddress;
    private String applicationName;
    private String applicationPwd;
    private Map<String, String> redundant;

    @Override
    public TbRunJobCallNodeConfiguration defaultConfiguration() {
        TbRunJobCallNodeConfiguration configuration = new TbRunJobCallNodeConfiguration();
        configuration.setServerAddress("127.0.0.1:7700");
        configuration.setApplicationName("hachi-iot-scheduler-worker");
        configuration.setApplicationPwd("123");
        configuration.setRedundant(Collections.emptyMap());
        return configuration;
    }
}
