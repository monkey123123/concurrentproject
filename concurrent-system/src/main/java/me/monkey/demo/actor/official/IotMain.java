package me.monkey.demo.actor.official;

import akka.actor.typed.ActorSystem;

public class IotMain {

  public static void main(String[] args) {
    // Create ActorSystem and top level supervisor
    ActorSystem<Void> actorSystem = ActorSystem.create(IotSupervisor.create(), "iot-system");

  }
}