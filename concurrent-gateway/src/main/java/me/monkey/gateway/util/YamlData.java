package me.monkey.gateway.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class YamlData {
    private static String name;
    @Value("${response.name}")
    public void setName(String name) {
        YamlData.name = name;
    }
    public static String getName() {
        return name;
    }
}