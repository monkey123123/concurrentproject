package me.monkey.gateway.other.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/*
https://www.jianshu.com/p/5b300bd4e6fe
 */
public class HelloWorldSimpleMain {

    /*
HelloWorld path :akka://Hello/user/helloworld
Greetor Actor path:akka://Hello/user/helloworld/greetor
Hello World
     */
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("HelloActorSystem", ConfigFactory.load("samplehello.conf"));
        ActorRef a = system.actorOf(Props.create(HelloWorld.class),"helloworld0000");
        System.err.println("----HelloWorld path :" + a.path());
    }
}