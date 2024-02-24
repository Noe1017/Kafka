package com.atguigu.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class CustomProducerTranactions {

    public static void main(String[] args) {

        // 1. 创建kafka生产者的配置对象
        Properties properties = new Properties();

        // 2. 给kafka配置对象添加配置信息：bootstrap.servers 47.122.43.5
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "47.122.43.5:9092");

        // key,value序列化（必须）：key.serializer，value.serializer
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 指定事务id

        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG,"tranactional_id_01");

        // 3. 创建kafka生产者对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        kafkaProducer.initTransactions();

        kafkaProducer.beginTransaction();

        try{
            for(int i = 0 ; i < 5 ; i ++){
                kafkaProducer.send(new ProducerRecord<>("first","atguigu" + i));
            }

       //     int i = 1/0;
        }catch (Exception e){
            kafkaProducer.abortTransaction();
        } finally{

            //3 关闭资源
            kafkaProducer.close();
        }

    }
}