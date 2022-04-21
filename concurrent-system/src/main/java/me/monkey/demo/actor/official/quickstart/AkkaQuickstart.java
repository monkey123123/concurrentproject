package me.monkey.demo.actor.official.quickstart;

import akka.actor.typed.ActorSystem;

import java.io.IOException;
/* from official:
What Hello World does
The example consists of three actors:

Greeter: Receives commands to Greet someone and responds with a Greeted to confirm the greeting has taken place
GreeterBot: receives the reply from the Greeter and sends a number of additional greeting messages and collect the replies until a given max number of messages have been reached.
GreeterMain: The guardian actor that bootstraps everything

 */
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
