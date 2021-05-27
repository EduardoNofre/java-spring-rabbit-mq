package com.api.java.spring.rabbit.mq.app.estudo.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.api.java.spring.rabbit.mq.app.estudo.model.RabbitMQModel;
import com.api.java.spring.rabbit.mq.app.estudo.util.Conexao;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * 
 * @author nofre
 *
 *         Consuming – O recepetor da mensagem
 *
 */
public class RabbitMQConsumer {
	
	private final static String NOME_FILA_MSG = "fila001msg";

	private final static String NOME_FILA_JSON = "fila001json";

	public void getMsg(String msg) throws java.io.IOException, java.lang.InterruptedException, TimeoutException {
		
        ConnectionFactory factory = Conexao.getConnectionFactory();

        Connection connection = factory.newConnection();
        
        Channel channel = connection.createChannel();

        channel.queueDeclare(NOME_FILA_MSG, false, false, false, null);

        Consumer consumer = new DefaultConsumer(channel) {
        	
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            	
                String message = new String(body, "UTF-8");
                
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(NOME_FILA_MSG, true, consumer);

	}

	public void getJson(RabbitMQModel rabbitMQModel) throws java.io.IOException, java.lang.InterruptedException, TimeoutException {
		
        ConnectionFactory factory = Conexao.getConnectionFactory();

        Connection connection = factory.newConnection();
        
        Channel channel = connection.createChannel();

        channel.queueDeclare(NOME_FILA_JSON, false, false, false, null);

        Consumer consumer = new DefaultConsumer(channel) {
        	
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            	
                String message = new String(body, "UTF-8");
         
            }
        };
        channel.basicConsume(NOME_FILA_JSON, true, consumer);

	}

}
