# Overview
This is a Spring Cloud Stream demo. This demo uses local RMQ running on your workstation.
You need Docker and docker-compose installed.

This demonstrate how to configure a Spring Cloud Stream application to publish and consume messages, to and from RMQ.
The messages are sent as a stream and consumed as a stream.
The abstraction is provided by following dependencies -

`<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-stream</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-stream-binder-rabbit</artifactId>
</dependency>`

## Step 1
Start the local RMQ setup

`docker-compose up -d`

This starts RMQ setup in your local Docker engine.
You can verify the setup using the management console URL -
http://localhost:15672/ (guest/guest)

## Step 2
Start the producer application. The producer is a web application that takes input from the REST endpoint and publishes that text to RMQ.
The RMQ destination is a channel that is specified in the `application.properties` file.
As you can see there is nothing else the properties file. This is to show that the SCSt assumes defaults for connection.

Make sure you the port 8080 open for this application. If not you can change the port using `server.port` in `application.properties`.

## Step 3
Star the consumer application. The consumer application consumes messages from the queue. SCSt abstraction takes care of creating the Queue and binding the queue with the channel. Here too, the RMQ destination is a channel that is specified in the `application.properties` file.

The application will consume the text from the queue and print the uppercase for the text.
You can observe the throughput and queue depth using the management console.
http://localhost:15672/ (guest/guest)
