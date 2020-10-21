package me.monkey.demo.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/*
https://www.jianshu.com/p/5b300bd4e6fe
 */
public class HelloWorldSimpleMain {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("Hello", ConfigFactory.load("samplehello.conf"));
        ActorRef a =system.actorOf(Props.create(HelloWorld.class),"helloworld");
        System.err.println("HelloWorld path :"+a.path());
    }
}