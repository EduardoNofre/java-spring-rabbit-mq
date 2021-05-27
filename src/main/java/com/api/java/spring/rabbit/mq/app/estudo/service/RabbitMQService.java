package com.api.java.spring.rabbit.mq.app.estudo.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.java.spring.rabbit.mq.app.estudo.model.RabbitMQModel;
import com.api.java.spring.rabbit.mq.app.estudo.rabbitmq.RabbitMQConsumer;
import com.api.java.spring.rabbit.mq.app.estudo.rabbitmq.RabbitMQProducing;

@Service
public class RabbitMQService {

	@Autowired
	private RabbitMQProducing rabbitMQProducing;
	
	@Autowired
	private RabbitMQConsumer rabbitMQConsumer;
	
	
	public String sendMsg(String msg) throws IOException, InterruptedException, TimeoutException {

		return rabbitMQProducing.sendMsg(msg);

	}

	public String getMsg(String msg) throws IOException, InterruptedException, TimeoutException {

		return rabbitMQConsumer.getMsg(msg);

	}

	public String sendJson(RabbitMQModel rabbitMQModel) throws IOException, InterruptedException, TimeoutException {

		return rabbitMQConsumer.getJson(rabbitMQModel);

	}

	public String getJson(RabbitMQModel rabbitMQModel) throws IOException, InterruptedException, TimeoutException {

		return rabbitMQConsumer.getJson(rabbitMQModel);

	}
}
