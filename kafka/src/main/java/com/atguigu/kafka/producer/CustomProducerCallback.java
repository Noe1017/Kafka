package com.atguigu.kafka.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class CustomProducerCallback {

    public static void main(String[] args) throws InterruptedException {

        // 1. 创建kafka生产者的配置对象
        Properties properties = new Properties();

        // 2. 给kafka配置对象添加配置信息：bootstrap.servers 47.122.43.5
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "47.122.57.72:9092,47.122.47.166:9092");

        // key,value序列化（必须）：key.serializer，value.serializer
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 3. 创建kafka生产者对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
        // 4. 调用send方法,发送消息
        for (int i = 0; i < 500; i++) {
            kafkaProducer.send(new ProducerRecord<>("first","atguigu " + i), new Callback(){
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception){

                    if( exception == null){
                        System.out.println("主题: " + metadata.topic() + "分区 :" + metadata.partition());
                    }
                }
            });
            Thread.sleep(1);
        }

        // 5. 关闭资源
        kafkaProducer.close();
    }
}