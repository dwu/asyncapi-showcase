package com.example.payment;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.support.RetrySimulation.SleepSequence;

@SpringBootApplication
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	private Random r = new Random();
	
	@Autowired
	private StreamBridge streamBridge;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public Consumer<OrderCreated> orderCreated() {
		return data -> {
			int sleepDuration = r.nextInt(10);
			
			logger.info("Payment received order " + data.toString() + ", sleeping for " + sleepDuration + " seconds");
			try {
				Thread.sleep(sleepDuration*1000l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			InvoiceCreated ic = new InvoiceCreated();
			ic.setId(UUID.randomUUID().toString());
			ic.setCustomerid(data.getCustomerid());
			ic.setInvoiceamount(BigDecimal.valueOf(r.nextInt(10000)));
			
			streamBridge.send("invoiceCreated-out-0", ic);
		};
	}

}
