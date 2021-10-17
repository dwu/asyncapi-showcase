package com.example.delivery;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public Consumer<OrderCreated> orderCreated() {
		return data -> {
			logger.info("Delivery received order " + data.toString() + ", preparing delivery");
		};
	}

	@Bean
	public Consumer<InvoiceCreated> invoiceCreated() {
		return data -> {
			logger.info("Delivery received invoice notification " + data.toString() + ", completing delivery");
		};
	}

}
