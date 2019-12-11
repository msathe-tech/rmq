package com.example.rmq;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.GenericMessage;

@EnableBinding(TextProcessor.class)
public class MessageConsumer {

	@Autowired
	TextProcessor processor;

	@StreamListener(TextProcessor.TEXT_IN)
	@SendTo(TextProcessor.UPPERCASE_TEXT_OUT)
	public String upperCase(String message) {
		String uppercase = message.toUpperCase();
		System.out.println("Message received: " + uppercase);

		MessageChannel output = processor.uppercaseTextOut();
		try {
			output.send(new GenericMessage<String>(uppercase));
		} catch (Exception e) {
			System.out.println("Exception - "+ e.getLocalizedMessage());
		}
		return "success";
	}

}

interface TextProcessor {
	public String TEXT_IN = "text";
	public String UPPERCASE_TEXT_OUT="uppercaseText";

	@Input(TEXT_IN)
	MessageChannel textIn();

	@Output(UPPERCASE_TEXT_OUT)
	MessageChannel uppercaseTextOut();
}