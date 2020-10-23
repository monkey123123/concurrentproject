package me.monkey.demo.pulsar;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.Schema;
 
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/*
2020-10-23 15:10:05 3808 [pulsar-client-io-1-1] INFO  o.a.p.c.i.ProducerStatsRecorderImpl - Starting Pulsar producer perf with config: {
  "topicName" : "persistent://public/default/my-topic",
  "producerName" : "my-producer",
  "sendTimeoutMs" : 10000,
  "blockIfQueueFull" : true,
  "maxPendingMessages" : 512,
  "maxPendingMessagesAcrossPartitions" : 50000,
  "messageRoutingMode" : "RoundRobinPartition",
  "hashingScheme" : "JavaStringHash",
  "cryptoFailureAction" : "FAIL",
  "batchingMaxPublishDelayMicros" : 10000,
  "batchingMaxMessages" : 1024,
  "batchingEnabled" : true,
  "compressionType" : "NONE",
  "initialSequenceId" : null,
  "autoUpdatePartitions" : true,
  "properties" : { }
}
2020-10-23 15:10:05 3810 [pulsar-client-io-1-1] INFO  o.a.p.c.i.ProducerStatsRecorderImpl - Pulsar client config: {
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
 * 3.2消息 发布制定主题 另一种方式
 * @author : nazi
 * @version : 1.0
 * @date : 2019/6/17 16:53
 * 异步
 */
@Slf4j
public class ProducerDemm {
    private static final String SERVER_URL = "pulsar://localhost:6650";
 
    public static void main(String[] args) throws Exception {
       // 构造Pulsar Client
        PulsarClient client = PulsarClient.builder()
                .serviceUrl(SERVER_URL)
                .build();
 
        // 构造生产者
        Producer<String> producer = client.newProducer(Schema.STRING)
                .producerName("my-producer")
                .topic("persistent://public/default/my-topic")
                .batchingMaxMessages(1024)
                .batchingMaxPublishDelay(10, TimeUnit.MILLISECONDS)
                .enableBatching(true)
                .blockIfQueueFull(true)
                .maxPendingMessages(512)
                .sendTimeout(10, TimeUnit.SECONDS)
                .blockIfQueueFull(true)
                .create();
 
        // 同步发送消息
        MessageId messageId = producer.send("Hello World");
        log.info("message id is {}", messageId);
        CompletableFuture<MessageId> asyncMessageId = producer.sendAsync("This is a async message");
        // 阻塞线程，直到返回结果
        log.info("async message id is {}",asyncMessageId.get());
 
        // 配置发送的消息元信息，同步发送
//        producer.newMessage()
//                .key("my-async-message-key")
//                .value("my-async-message")
//                .property("my-async-key", "my-async-value")
//                .property("my-async-other-key", "my-async-other-value")
//                .sendAsync();
 
        // 关闭producer的方式有两种：同步和异步
         producer.close();
 
        // 关闭licent的方式有两种，同步和异步
        client.close();
 
    }
 
}