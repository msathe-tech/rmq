package com.example.rmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

@SpringBootApplication
public class RmqPublisherApplication {

	public static void main(String[] args) {
		SpringApplication.run(RmqPublisherApplication.class, args);
	}


}

