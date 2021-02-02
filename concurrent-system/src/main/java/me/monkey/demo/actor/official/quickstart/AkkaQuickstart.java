package me.monkey.demo.actor.official.quickstart;

import akka.actor.typed.ActorSystem;

import java.io.IOException;
public class AkkaQuickstart {
  public static void main(String[] args) {
    //#actor-system
    final ActorSystem<GreeterMain.SayHello> actorSystem = ActorSystem.create(GreeterMain.create(), "helloakka");
    //#actor-system

    //#main-send-messages
    actorSystem.tell(new GreeterMain.SayHello("Charles"));
    //#main-send-messages

    try {
      System.out.println(">>> Press ENTER to exit <<<");
      System.in.read();
    } catch (IOException ignored) {
    } finally {
      actorSystem.terminate();
    }
  }
}
