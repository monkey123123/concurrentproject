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
    actor模型其实是更加面向对象的一种方式（OOP），actor对象有行为和数据，外部只要告诉actor消息，actor会根据自身状态选择合适的行为。在之前的编码中，我们都是通过类封装了数据和行为，并且向外部暴露这些行为，其实这样我们只是封装了数据但是使用时还要关注行为，与actor模型相比不够面向对象
    链接：https://www.jianshu.com/p/5b300bd4e6fe

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