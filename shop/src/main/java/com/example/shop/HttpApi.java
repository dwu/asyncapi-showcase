package com.example.shop;

import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpApi {

	private static final Logger logger = LoggerFactory.getLogger(HttpApi.class);
	
	private Random r = new Random();
	
	@Autowired
	private StreamBridge streamBridge;
	
	@RequestMapping(method = RequestMethod.POST, path = "/create-order")
	public String createOrder() {
		logger.info("Shop received order request");
		
		OrderCreated oc = new OrderCreated();
		oc.setId(UUID.randomUUID().toString());
		oc.setCustomerid(UUID.randomUUID().toString());
		oc.setProductid(UUID.randomUUID().toString());
		oc.setAmount(r.nextInt(100));
		
		streamBridge.send("orderCreated-out-0", oc);
		
		return "OK";
	}
	
}
