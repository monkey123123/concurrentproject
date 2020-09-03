package me.monkey.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandLineRunnerDemo implements CommandLineRunner {

    @Autowired
    private TestDemo testDemo;

    @Override
    public void run(String... args) throws Exception {
        List list = null;
        testDemo.checkReport();
        System.out.println("---------CommandLineRunner---------run");
    }
}
