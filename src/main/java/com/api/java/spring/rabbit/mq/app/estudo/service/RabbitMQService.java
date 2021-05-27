package com.api.java.spring.rabbit.mq.app.estudo.service;

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
	
	
	public String sendMsg(String msg) {

		return rabbitMQProducing.sendMsg(msg);

	}

	public String getMsg(String msg) {

		return rabbitMQConsumer.getMsg(msg);;

	}

	public RabbitMQModel sendJson(RabbitMQModel rabbitMQModel) {

		return rabbitMQConsumer.getJson(rabbitMQModel);

	}

	public RabbitMQModel getJson(RabbitMQModel rabbitMQModel) {

		return rabbitMQConsumer.getJson(rabbitMQModel);

	}
}
