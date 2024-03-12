一、概述
    定义
         1) 传统定义
            分布式    发布订阅    消息队列
            发布订阅：分为多种类型    订阅者根据需求    选择性订阅
         2) 最新定义
            流平台(存储、计算)
    2、 消息队列应用场景
         1) 缓存消峰
         2) 解耦
         3) 异步通信 
    3、 两种模式
         1) 点对点
             (1) 一个生产者，一个消费者，一个topic，会删除数据 不多
         2) 发布订阅 
             (1) 多个生产者 消费者多个 而且相互独立 多个topic 不会删除数据
    4、 架构
        1) 生产者
            100T数据

         2) broker
            (1) broker 服务器 node1 node2 node3
            (2) topic 主题 对数据分类
            (3) 分区
            (4) 可靠性 副本
            (5) leader follwer 
            (6) 生产者和消费者 只针对leader 操作

         3) 消费者 
            (1) 消费者和消费者相互独立
            (2) 消费者组(某个分区 只能由一个消费者 消费)

        4) zookeeper 
            (1) broker.ids 0 1 2
            (2) leader 
二、安装
    1、生产者
        1) broker.id 必须全局唯一
        2) broker.id 、log.dirs zk/kafka
        3) 启动停止 先停止kafka 再停止zk
        4) 脚本

```
# !/bin/bash

    case $1 in
    "start")
        for i in node1 node2 node3
        do
            ssh $i "绝对路径"
        done
    ;;
    "stop")
    
    ;;
    esac
```
```
    2、常用命令行
        1)主题 kafka-topic.sh
                (1) --bootstrap-server node1:9092,node2:9092
                (2) --topic first
                (3) --create
                (4) --delete
                (5) --alter
                (6) --list
                (7) --describe
                (8) --partitions
                (9) --replication-factor
        2)生产者 kafka-console-producer.sh
                (1) --bootstrap-server node1:9092,node2:9092
                (2) --topic first
        3)消费者 kafka-console-consumer.sh
                (1) --bootstrap-server node1:9092,node2:9092
                (2) --topic first
```
