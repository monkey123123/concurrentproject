package me.monkey.gateway.service;

import me.monkey.gateway.controller.User;

public interface TestService {
    void run();

    void runScheduled();
    void testPlaceholder1();
    void testPlaceholder2();
    void testTransaction();


    User findUserById(Long id);
}
