package com.zzr.framework.kafka;

import java.util.Properties;

/**
 * Created by zhaozhirong on 2019/8/28.
 */
public class KafkaConfig {

    public static final String TOPIC = "test";

    public static Properties getProducerConfig(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "140.143.238.46:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }

    public static Properties getConsumerConfig(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "140.143.238.46:9092");
        props.put("group.id", "test1");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }
}
