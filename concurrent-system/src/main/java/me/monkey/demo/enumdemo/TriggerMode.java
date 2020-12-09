package me.monkey.demo.enumdemo;

import java.util.Optional;

public enum TriggerMode {
    TIMER("0"),
    DEVICE("1");

    private String code;

    TriggerMode(String i) {
        this.code = i;
    }

    public Optional<String> getValue() {
        return Optional.ofNullable(this.code);
    }
}