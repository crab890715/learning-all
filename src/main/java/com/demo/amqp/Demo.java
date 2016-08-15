package com.demo.amqp;

import java.io.IOException;

import org.springframework.util.SerializationUtils;

import com.demo.mongo.model.User;
import com.demo.mongo.utils.ThreadPoolUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import com.rabbitmq.client.AMQP.BasicProperties;

public class Demo {
	 public void conn() throws IOException{
         
         //Create a connection factory
         ConnectionFactory factory = new ConnectionFactory();
         
         //hostname of your rabbitmq server
         factory.setHost("192.168.22.111");
         factory.setUsername("guest");
         factory.setPassword("guest");
         factory.setPort(5672);
         //getting a connection
         Connection connection = factory.newConnection();
         
         //creating a channel
         final Channel channel = connection.createChannel();
         channel.queueDeclare("test", false, false, false, null);
         //declaring a queue for this channel. If queue does not exist,
         //it will be created on the server.
         ThreadPoolUtils.getExecutor().execute(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				 try {
					channel.basicConsume("test", true,new Consumer() {
						@Override
						public void handleShutdownSignal(String arg0, ShutdownSignalException arg1) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void handleRecoverOk(String arg0) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void handleDelivery(String arg0, Envelope arg1, BasicProperties arg2, byte[] arg3) throws IOException {
							User user = (User)SerializationUtils.deserialize(arg3);
					        System.out.println("Message Number "+ user.getName() + " received.");
							
						}
						
						@Override
						public void handleConsumeOk(String arg0) {
							// TODO Auto-generated method stub
							 System.out.println("Consumer "+arg0 +" registered"); 
						}
						
						@Override
						public void handleCancelOk(String arg0) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void handleCancel(String arg0) throws IOException {
							// TODO Auto-generated method stub
							
						}
					});
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
         User u = new User();
         u.setName("1111");
         channel.basicPublish("","test", null, SerializationUtils.serialize(u));
         System.err.println("***********************end**************************");
    }
	 public static void main(String[] args) throws IOException {
		new Demo().conn();
	}
}
