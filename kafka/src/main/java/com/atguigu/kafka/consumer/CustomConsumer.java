package com.atguigu.kafka.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.lang.invoke.StringConcatFactory;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

public class CustomConsumer {

    public static void main(String [] args){


        //0 配置
        Properties properties = new Properties();

        // 连接
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"47.122.57.72:9092,47.122.47.166:9092");

        // 反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());

        // 配置消费者id
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"test5");

        //设置分配分区策略
        properties.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG,"org.apache.kafka.clients.consumer.StickyAssignor");

        // 1 创建 一个消费者

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);

        // 2 定义主题 first
        ArrayList<String> topics = new ArrayList<>();
        topics.add("first");
        kafkaConsumer.subscribe(topics);

        //3 消费数据
        while(true){

            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));

            for(ConsumerRecord<String,String> consumerRecord : consumerRecords){
                System.out.println(consumerRecord);
            }
        }
    }
}
