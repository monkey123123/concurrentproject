package me.monkey.demo.pulsar;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.SubscriptionType;
 
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


/*
2020-10-23 15:00:44 2797 [pulsar-client-io-1-1] INFO  o.a.p.client.impl.ConnectionPool - [[id: 0xfa9b9e61, L:/127.0.0.1:62540 - R:localhost/127.0.0.1:6650]] Connected to server
2020-10-23 15:00:44 3131 [pulsar-client-io-1-1] INFO  o.a.p.c.i.ConsumerStatsRecorderImpl - Starting Pulsar consumer perf with config: {
  "topicNames" : [ "topic1" ],
  "topicsPattern" : null,
  "subscriptionName" : "my-sub",
  "subscriptionType" : "Exclusive",
  "receiverQueueSize" : 1000,
  "acknowledgementsGroupTimeMicros" : 100000,
  "maxTotalReceiverQueueSizeAcrossPartitions" : 50000,
  "consumerName" : null,
  "ackTimeoutMillis" : 10000,
  "tickDurationMillis" : 1000,
  "priorityLevel" : 0,
  "cryptoFailureAction" : "FAIL",
  "properties" : { },
  "readCompacted" : false,
  "subscriptionInitialPosition" : "Latest",
  "patternAutoDiscoveryPeriod" : 1,
  "regexSubscriptionMode" : "PersistentOnly",
  "deadLetterPolicy" : null,
  "autoUpdatePartitions" : true
}
2020-10-23 15:00:44 3134 [pulsar-client-io-1-1] INFO  o.a.p.c.i.ConsumerStatsRecorderImpl - Pulsar client config: {
  "serviceUrl" : "pulsar://localhost:6650",
  "operationTimeoutMs" : 30000,
  "statsIntervalSeconds" : 60,
  "numIoThreads" : 1,
  "numListenerThreads" : 1,
  "connectionsPerBroker" : 1,
  "useTcpNoDelay" : true,
  "useTls" : false,
  "tlsTrustCertsFilePath" : "",
  "tlsAllowInsecureConnection" : false,
  "tlsHostnameVerificationEnable" : false,
  "concurrentLookupRequest" : 5000,
  "maxLookupRequest" : 50000,
  "maxNumberOfRejectedRequestPerConnection" : 50,
  "keepAliveIntervalSeconds" : 30,
  "connectionTimeoutMs" : 10000
}
 */
/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/6/13 19:47
 */
public class MessageConsumer {
    private Client client;
    private Consumer consumer;
 
    public MessageConsumer(String topic, String subscription) throws PulsarClientException {
        client = new Client();
        consumer = createConsumer(topic, subscription);
    }
 
    private Consumer createConsumer(String topic, String subscription) throws PulsarClientException {
 
        return client.getPulsarClient().newConsumer().topic(topic).subscriptionName(subscription)
                .ackTimeout(10, TimeUnit.SECONDS).subscriptionType(SubscriptionType.Exclusive).subscribe();
    }
 
    public String getMessage() throws ExecutionException, InterruptedException, PulsarClientException {
        /***
         * 获取一次，就关闭会话
         */
        // Wait for a message
        System.out.printf("Start pulsar");
        CompletableFuture<Message> msg = consumer.receiveAsync();
 
        // System.out.printf("Message received: %s", new String(msg.get().getData()));
        String result = "topic is: " + msg.get().getTopicName() + ",data is: " + new String(msg.get().getData());
 
        // Acknowledge the message so that it can be deleted by the message broker
        consumer.acknowledge(msg.get());
        consumer.close();
        client.Close();
        return result;
    }
 
    public void receiveMessage() throws ExecutionException, InterruptedException, PulsarClientException {
        /***
         * 用来异步获取，保持回话
         */
        do {
            // Wait for a message
            CompletableFuture<Message> msg = consumer.receiveAsync();
 
            System.out.printf("Message received: %s", new String(msg.get().getData()));
 
            // Acknowledge the message so that it can be deleted by the message broker
            consumer.acknowledge(msg.get());
        } while (true);
    }
 
    public static void main(String[] args) throws PulsarClientException, ExecutionException, InterruptedException {
        MessageConsumer consumer = new MessageConsumer("topic1", "my-sub");
        consumer.receiveMessage();
//		String reString = consumer.getMessage();
//		System.err.println(reString);
        // consumer.client.Close();
 
    }
}