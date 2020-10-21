package me.monkey.demo.actor;

import akka.actor.UntypedActor;

public class Greeter extends UntypedActor {
    public static enum Msg{
        GREET,DONE;
    }
    @Override
    public void onReceive(Object arg0)  {
        if(arg0==Msg.GREET){
            System.err.println("Hello World");
            getSender().tell(Msg.DONE, getSelf());
        }else{
            unhandled(arg0);
        }
    }
}