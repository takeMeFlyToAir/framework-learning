
#####查看主题列表
```
bin/kafka-topics.sh --list --zookeeper localhost:2181
```


#####创建主题
```
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic streams-plaintext-input
```


#####查看主题详情
```
./bin/kafka-topics.sh --zookeeper localhost:2181 --describe --topic test
```


#####发送消息
```
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic streams-plaintext-input
```
#####消费消息
```
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic streams-wordcount-output --from-beginning
```

#####修改主题分区
```
./bin/kafka-topics.sh --alter  --zookeeper localhost:2181  --topic test  --partitions 3
```