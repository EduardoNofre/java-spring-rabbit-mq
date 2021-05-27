package com.api.java.spring.rabbit.mq.app.estudo.rabbitmq;

import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Component;

import com.api.java.spring.rabbit.mq.app.estudo.model.RabbitMQModel;
import com.api.java.spring.rabbit.mq.app.estudo.util.Conexao;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 
 * @author nofre
 * 
 *         Producing – O produtor da mensagem
 *
 */
@Component
public class RabbitMQProducing {

	private final static String NOME_FILA_MSG = "fila001msg";

	private final static String NOME_FILA_JSON = "fila001json";

	public String sendMsg(String msg) throws java.io.IOException, java.lang.InterruptedException, TimeoutException {

		ConnectionFactory factory = Conexao.getConnectionFactory();

		try (Connection connection = factory.newConnection()) {

			Channel channel = connection.createChannel();

			channel.queueDeclare(NOME_FILA_MSG, false, false, false, null);

			channel.basicPublish("", NOME_FILA_MSG, null, msg.getBytes());

			System.out.println(" [x] Sent '" + msg + "'");

			channel.close();

		} catch (Exception e) {
			
			return "Não foi possível criar conexão\n";
		}
		return "sucesso";

	}

	public String sendMsg(RabbitMQModel rabbitMQModel) throws java.io.IOException, java.lang.InterruptedException, TimeoutException {

		ConnectionFactory factory = Conexao.getConnectionFactory();

		try (Connection connection = factory.newConnection()) {

			Channel channel = connection.createChannel();

			channel.queueDeclare(NOME_FILA_JSON, false, false, false, null);

			channel.basicPublish("", NOME_FILA_JSON, null, converterobjetotoJson(rabbitMQModel).getBytes());

			channel.close();

		} catch (Exception e) {
			
			return "Não foi possível criar conexão\n";
		}
		return "sucesso";
	}

	private String converterobjetotoJson(RabbitMQModel rabbitMQModel) {

		return new Gson().toJson(rabbitMQModel);

	}
}
