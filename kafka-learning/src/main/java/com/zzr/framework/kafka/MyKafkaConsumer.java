package com.zzr.framework.kafka;


import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by zhaozhirong on 2019/8/28.
 */
public class MyKafkaConsumer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        commonReceive5();
    }

    /**
     * 接收，自动提交偏移量
     */
    private static void commonReceive1() throws ExecutionException, InterruptedException {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(KafkaConfig.getConsumerConfig());
        consumer.subscribe(Arrays.asList(KafkaConfig.TOPIC));
        while (true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("partition = %d,offset = %d, key = %s, value = %s%n", record.partition(),record.offset(), record.key(), record.value());
            }
        }
    }

    /**
     * 接收，手动提交偏移量
     */
    private static void commonReceive2() throws ExecutionException, InterruptedException {
        Properties props = KafkaConfig.getConsumerConfig();
        //设置自动提交为false
        props.put("enable.auto.commit", "false");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(KafkaConfig.TOPIC));
        final int minBatchSize = 200;
        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : records) {
                buffer.add(record);
            }
            if (buffer.size() >= minBatchSize) {
                insertIntoDb(buffer);
                consumer.commitSync();
                buffer.clear();
            }
        }
    }

    private static void insertIntoDb(List<ConsumerRecord<String, String>> buffer){

    }

    /**
     * 接收，手动提交偏移量----处理完一个分区后，即提交偏移量
     */
    private static void commonReceive3() throws ExecutionException, InterruptedException {
        Properties props = KafkaConfig.getConsumerConfig();
        //设置自动提交为false
        props.put("enable.auto.commit", "false");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(KafkaConfig.TOPIC));
        while(true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            for (TopicPartition partition : records.partitions()) {
                List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
                for (ConsumerRecord<String, String> record : partitionRecords) {
                    System.out.println(record.offset() + ": " + record.value());
                }
                long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
                consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
            }
        }
    }

    /**
     * 订阅指定的分区
     */
    private static void commonReceive4() throws ExecutionException, InterruptedException {
        Properties props = KafkaConfig.getConsumerConfig();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        TopicPartition partition = new TopicPartition(KafkaConfig.TOPIC,0);
        consumer.assign(Arrays.asList(partition));
        while(true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("partition = %d,offset = %d, key = %s, value = %s%n", record.partition(),record.offset(), record.key(), record.value());
            }
        }
    }

    /**
     * 接收，手动提交偏移量
     */
    private static void commonReceive5() throws ExecutionException, InterruptedException {
        Properties props = KafkaConfig.getConsumerConfig();
        //设置自动提交为false
        props.put("enable.auto.commit", "false");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        LinkedBlockingQueue<Map<TopicPartition, OffsetAndMetadata>> commitQueue = new LinkedBlockingQueue<>();
        consumer.subscribe(Arrays.asList(KafkaConfig.TOPIC), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                //提交偏移量 主要是consumer.commitSync(toCommit); 方法
                System.out.println("*- in ralance:onPartitionsRevoked");
                //commitQueue 是我本地已消费消息的一个队列 是一个linkedblockingqueue对象
                while (!commitQueue.isEmpty()) {
                    Map<TopicPartition, OffsetAndMetadata> toCommit = commitQueue.poll();
                    consumer.commitSync(toCommit);
                }
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
            //rebalance之后 获取新的分区，获取最新的偏移量，设置拉取分量
                System.out.println("*- in ralance:onPartitionsAssigned  ");
                for (TopicPartition partition : partitions) {
                    System.out.println("*- partition:"+partition.partition());
                    //获取消费偏移量，实现原理是向协调者发送获取请求
                    OffsetAndMetadata offset = consumer.committed(partition);
                    //设置本地拉取分量，下次拉取消息以这个偏移量为准
                    consumer.seek(partition, offset.offset());
                }
            }
        });
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            Set<TopicPartition> partitions = records.partitions();
            for (TopicPartition partition : partitions) {
                List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
                for (ConsumerRecord<String, String> partitionRecord : partitionRecords) {
                    System.out.printf("partition = %d,offset = %d, key = %s, value = %s%n", partitionRecord.partition(),partitionRecord.offset(), partitionRecord.key(), partitionRecord.value());
                    commitQueue.add(Collections.singletonMap(partition, new OffsetAndMetadata(partitionRecord.offset() + 1)));
                }
            }

        }
    }

}
