package com.example.kafka.consumers;

import com.example.spark.request.SparkRequest;
import com.example.spark.response.SparkResponse;

public interface IKafkaConsumers {

    public void listen(SparkRequest request);

    public void listen(SparkResponse message);
}
