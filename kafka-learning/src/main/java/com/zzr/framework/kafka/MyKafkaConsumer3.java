package com.zzr.framework.kafka;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

/**
 * Created by zhaozhirong on 2019/8/28.
 */
public class MyKafkaConsumer3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        commonReceive();
    }

    /**
     * 接收，自动提交偏移量
     */
    private static void commonReceive() throws ExecutionException, InterruptedException {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(KafkaConfig.getConsumerConfig());
        consumer.subscribe(Arrays.asList("test"));
        while (true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("partition = %d,offset = %d, key = %s, value = %s%n", record.partition(),record.offset(), record.key(), record.value());
            }
        }
    }

}
