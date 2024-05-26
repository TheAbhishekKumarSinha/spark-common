package com.example.kafka.config;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.example.spark.request.SparkRequest;
import com.example.spark.response.SparkResponse;


@Configuration
public class KafkaProducerConfig {
    
    @Bean
    public ProducerFactory<String, SparkRequest> producerFactory(){
        JsonSerializer<SparkRequest> jsonSerializer = new JsonSerializer<>();
        jsonSerializer.setAddTypeInfo(true);
        return new DefaultKafkaProducerFactory<>(producerConf(), new StringSerializer(), jsonSerializer);
    }

    @Bean
    public Map<String, Object> producerConf() {
        Map<String, Object> producerConf = new HashMap<>();
        producerConf.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        producerConf.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerConf.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return producerConf;
    }

    @Bean
    @Qualifier
    public KafkaTemplate<String, SparkRequest> kafkaTemplate() {
        return new KafkaTemplate<String, SparkRequest>(producerFactory());
    }

    @Bean
    public ProducerFactory<String, SparkResponse> producerSparkResponseFactory(){
        JsonSerializer<SparkResponse> jsonSerializer = new JsonSerializer<>();
        jsonSerializer.setAddTypeInfo(true);
        return new DefaultKafkaProducerFactory<>(producerConf(), new StringSerializer(), jsonSerializer);
    }

    @Bean
    @Qualifier
    public KafkaTemplate<String, SparkResponse> kafkaResponseTemplate() {
        return new KafkaTemplate<String, SparkResponse>(producerSparkResponseFactory());
    }


    // @Bean
    // public KafkaTemplate<String, byte[]> bytesTemplate(ProducerFactory<String, byte[]> pf) {
    //     return new KafkaTemplate<>(pf,
    //             Collections.singletonMap(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class));
    // }
}