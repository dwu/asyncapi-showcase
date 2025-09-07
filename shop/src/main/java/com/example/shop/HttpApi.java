package com.example.shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

@RestController
public class HttpApi {

	private static final Logger logger = LoggerFactory.getLogger(HttpApi.class);
	
	private final Random r = new Random();
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@RequestMapping(method = RequestMethod.POST, path = "/create-order")
	public String createOrder() {
		logger.info("Shop received order request");
		
		OrderCreated oc = new OrderCreated();
		oc.setId(UUID.randomUUID().toString());
		oc.setCustomerid(UUID.randomUUID().toString());
		oc.setProductid(UUID.randomUUID().toString());
		oc.setAmount(r.nextInt(100));

        logger.info("Created order with id {}", oc.getId());
		rabbitTemplate.convertAndSend(Config.TOPIC_ORDERS, "orders.created", oc);
		
		return "OK";
	}
	
}
