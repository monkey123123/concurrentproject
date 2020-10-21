package me.monkey.demo.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class HelloWorld extends UntypedActor {

    ActorRef greetor;
    
         //preStart是生命周期函数，在actor启动时调用
    @Override
    public void preStart() throws Exception {
        greetor = getContext().actorOf(Props.create(Greeter.class), "greetor");
        System.err.println("Greetor Actor path:"+greetor.path());
                //发送GREET消息
        greetor.tell(Greeter.Msg.GREET, getSelf());
    }
    @Override
    public void onReceive(Object msg)  {
        if(msg==Greeter.Msg.DONE){
                        //停止actor
            getContext().stop(getSelf());
        }else{
            unhandled(msg);
        }
    }
}