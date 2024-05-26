package com.example.kafka.producers;

import com.example.spark.request.SparkRequest;
import com.example.spark.response.SparkResponse;

public interface IKafkaProducers {

    public void sendToKafka(final SparkRequest data);

    public void sendToKafka(final SparkResponse data);

}
