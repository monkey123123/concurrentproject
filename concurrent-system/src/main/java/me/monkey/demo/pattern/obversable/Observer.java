package me.monkey.demo.pattern.obversable;

public interface Observer {
    void update(Observable o, Object arg);
}