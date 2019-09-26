package com.zzr.framework.kafka;



import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by zhaozhirong on 2019/8/28.
 */
public class MyKafkaProducer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        commonSend();
    }


    /**
     * 普通发送：异步
     */
    private static void commonSend() throws ExecutionException, InterruptedException {
        KafkaProducer<String, String> producer = new KafkaProducer<>(KafkaConfig.getProducerConfig());
        for (int i = 0; i < 300; i++) {
            producer.send(new ProducerRecord<String, String>("test", Integer.toString(i), Integer.toString(i)));
            Thread.sleep(1000);
        }
        producer.close();
    }


    /**
     * 普通发送：同步阻塞发送
     * 使用 .get方法，形成阻塞
     */
    private void commonSendForGet() throws ExecutionException, InterruptedException {
        KafkaProducer<String, String> producer = new KafkaProducer<>(KafkaConfig.getProducerConfig());
        for (int i = 0; i < 100; i++) {
            Future<RecordMetadata> test = producer.send(new ProducerRecord<String, String>("test", Integer.toString(i), Integer.toString(i)));
            System.out.println(test.get().toString());
        }
        producer.close();
    }

    /**
     * 普通发送：异步回调发送
     */
    private void commonSendForCallBack() throws ExecutionException, InterruptedException {
        KafkaProducer<String, String> producer = new KafkaProducer<>(KafkaConfig.getProducerConfig());
        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<String, String>("test", Integer.toString(i), Integer.toString(i)), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    System.out.println(recordMetadata.toString());
                }
            });
        }
        producer.close();
    }
}
