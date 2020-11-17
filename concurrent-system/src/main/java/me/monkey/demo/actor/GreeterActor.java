package me.monkey.demo.actor;

import akka.actor.UntypedActor;

public class GreeterActor extends UntypedActor {
    public static enum Msg{
        GREET,DONE;
    }
    @Override
    public void onReceive(Object msg)  {
        System.out.println(getSender()+"   ------"+msg.toString());
        if(msg==Msg.GREET){
            System.err.println("GreeterActor onReceive----");
            getSender().tell(Msg.DONE, getSelf());
//            getSender().tell("dfdfdfsdfsdf", getSelf());
        }else{
            unhandled(msg);
        }
    }
}