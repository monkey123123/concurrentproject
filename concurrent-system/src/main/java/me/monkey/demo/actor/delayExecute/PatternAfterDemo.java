package me.monkey.demo.actor.delayExecute;

import akka.actor.ActorSystem;
import akka.dispatch.Futures;
import akka.dispatch.OnComplete;
import akka.pattern.Patterns;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
 
import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * Created by liyanxin on 2015/1/9.
 */
public class PatternAfterDemo {
    public static void main(String args[]) {
 
        ActorSystem system = ActorSystem.create("myActorSystem");
        final ExecutionContext ec = system.dispatcher();
 
        final Future<String> failExc = Futures.failed(new IllegalStateException("OHNOES1"));
        Future<String> delayed = Patterns.after(Duration.create(100000, "millis"),
                system.scheduler(), ec, failExc);
        Future<String> future = Futures.future(new Callable<String>() {
            @Override
            public String call() throws InterruptedException {
                Thread.sleep(5000);
                return "foo";
            }
        }, ec);
        Future<String> result = Futures.firstCompletedOf(Arrays.asList(future, delayed), ec);
        result.onComplete(new OnComplete<String>() {
            @Override
            public void onComplete(Throwable failure, String success) throws Throwable {
                if (failure != null) {
                    failure.printStackTrace();
                } else {
                    System.out.println(success);
                }
            }
        }, ec);
    }
}