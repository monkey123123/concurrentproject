package me.monkey.demo.pattern.staticfactory;

import org.springframework.mail.MailSender;



public class SendFactory {
    
    public static Sender produceMail(){
        return new MailSender();
    }
    
    public static Sender produceSms(){
        return new SmsSender();
    }
}

public class FactoryTest {  
  
    public static void main(String[] args) {      
        Sender sender = SendFactory.produceMail();  
        sender.Send();  
    }  
} 