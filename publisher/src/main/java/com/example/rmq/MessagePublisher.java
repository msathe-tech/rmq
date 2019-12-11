package com.example.rmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(TextSource.class)
public class MessagePublisher {

	@Autowired
	TextSource source;

	@GetMapping("/{message}")
	public String sendMessage(@PathVariable String message) {
		System.out.println("Sending: " + message);
		MessageChannel output = source.textOut();
		try {
			output.send(new GenericMessage<String>(message));
		} catch (Exception e) {
			System.out.println("Exception - "+ e.getLocalizedMessage());
			return "failed";
		}
		return "success";
	}

}

interface TextSource {

	public String TEXT_OUT = "text";

	@Output("text")
	MessageChannel textOut();
}

