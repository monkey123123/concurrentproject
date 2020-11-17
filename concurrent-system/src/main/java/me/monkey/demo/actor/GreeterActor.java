package me.monkey.demo.actor;

import akka.actor.UntypedActor;

public class GreeterActor extends UntypedActor {
    public static enum Msg{
        GREET,DONE;
    }
    @Override
    public void onReceive(Object arg0)  {
        if(arg0==Msg.GREET){
            System.err.println("GreeterActor onReceive----");
            getSender().tell(Msg.DONE, getSelf());
            getSender().tell("dfdfdfsdfsdf", getSelf());
        }else{
            unhandled(arg0);
        }
    }
}