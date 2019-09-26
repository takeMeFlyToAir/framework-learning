#####开启远程访问，java客户端远程连接的时候，需要开启kafka配置文件中的配置项
```
原来
advertised.listeners=PLAINTEXT://your.host.name:9092
更改为本机的ip地址，java客户端可以连接的地址，一般为服务器外网地址
advertised.listeners=PLAINTEXT://140.143.238.46:9092
```
#####zk和kafka启动的时候，jvm内存有时候不够，需要更改jvm内存大小限制，更改文件为
```
kafka-server-start.sh
zookeeper-server-start.sh
```

#####kafka可以利用不同的分组，同时消费一个topic的所有信息
```
props.put("group.id", "配置不同的分组即可");
```
#####手动控制偏移量
```
props.put("enable.auto.commit", "false");
```

#####kafka分区
```
假如kafka分区是3个，并且有3个消费端，那么每个消费端会各自被分一个分区消息，如果运行过程中，一个消费端
宕机，则由其他消费端消费数据

注意，手动分配分区（即，assgin）和动态分区分配的订阅topic模式（即，subcribe）不能混合使用。


```
