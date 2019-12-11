package com.example.rmq;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageChannel;

@EnableBinding(TextProcessor.class)
public class MessageConsumer {

	@StreamListener(TextProcessor.TEXT_IN)
	public void upperCase(String message) {
		System.out.println("Message received: " + message.toUpperCase());
	}

}

interface TextProcessor {
	public String TEXT_IN = "text";

	@Input(TEXT_IN)
	MessageChannel textOut();
}