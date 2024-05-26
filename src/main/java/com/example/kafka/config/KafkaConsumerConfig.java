package com.example.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.spark.request.SparkRequest;
import com.example.spark.response.SparkResponse;



    @Configuration
    public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, SparkRequest> consumerFactory() {
        
        //configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "");
        JsonDeserializer<SparkRequest> payloadJsonDeserializer = new JsonDeserializer<>();
        payloadJsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(consumerConf(), new StringDeserializer(), payloadJsonDeserializer);
    }

    @Bean
    public ConsumerFactory<String, SparkResponse> consumerResponseFactory() {
        
        //configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "");
        JsonDeserializer<SparkResponse> payloadJsonDeserializer = new JsonDeserializer<>();
        payloadJsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(consumerConf(), new StringDeserializer(), payloadJsonDeserializer);
    }

    @Bean
    public Map<String, Object> consumerConf() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group-id");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return configProps;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SparkRequest> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, SparkRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SparkResponse> kafkaListenerResponseContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, SparkResponse> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerResponseFactory());
        return factory;
    }

}
