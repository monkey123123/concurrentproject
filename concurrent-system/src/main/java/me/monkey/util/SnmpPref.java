package me.monkey.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;


/***
 * Snmp协议相关配置参数
 */
@ConfigurationProperties(prefix = "snmp.config")
public class SnmpPref {

    public static String v3User;
    
    public static String password;
    
    public static boolean isSnmp4JLogEnabled;


    @Value("${snmp.config.v3User}")
    public void setV3User(String v3User) {
        SnmpPref.v3User = v3User;
    }

    @Value("${snmp.config.password}")
    public void setPassword(String password) {
        SnmpPref.password = password;
    }

    @Value("${snmp.config.isSnmp4JLogEnabled}")
    public void setIsSnmp4JLogEnabled(boolean isSnmp4JLogEnabled) {
        SnmpPref.isSnmp4JLogEnabled = isSnmp4JLogEnabled;
    }

    public static String getUser() {
        return v3User;
    }

    public static String getPassword() {
        return password;
    }

    public static boolean isSnmp4jLogEnabled() {
        return isSnmp4JLogEnabled;
    }
}