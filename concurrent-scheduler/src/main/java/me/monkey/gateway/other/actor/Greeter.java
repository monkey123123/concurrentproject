package me.monkey.gateway.other.actor;

import akka.actor.UntypedActor;

public class Greeter extends UntypedActor {
    public static enum Msg{
        GREET,DONE;
    }
    @Override
    public void onReceive(Object arg0)  {
        if(arg0== Msg.GREET){
            System.err.println("inner Greeter.onReceive() --------- Hello World");
            getSender().tell(Msg.DONE, getSelf());
        }else{ //不处理消息
            unhandled(arg0);
        }
    }
    /*
    需要实现onReceive方法，根据收到的消息进行处理，
    上面代码如果收到的消息是Msg.GREET则返回Msg.DONE，否则不处理消息
     */
}