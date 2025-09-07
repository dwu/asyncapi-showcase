package com.example.payment;

import com.example.shop.InvoiceCreated;
import com.example.shop.OrderCreated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

@Component
public class Listener {

    private static final Logger logger = LoggerFactory.getLogger(Listener.class);

    private Random r = new Random();

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "#{anonymousOrdersQueue.name}")
    public void receiveMessage(OrderCreated orderCreated) {
        int sleepDuration = r.nextInt(10);

        logger.info("Received order with id {}, sleeping for {} seconds", orderCreated.getId(), sleepDuration);
        try {
            Thread.sleep(sleepDuration * 1000L);
        } catch (InterruptedException e) {
            logger.error("Error during wait", e);
        }

        InvoiceCreated ic = new InvoiceCreated();
        ic.setId(UUID.randomUUID().toString());
        ic.setCustomerid(orderCreated.getCustomerid());
        ic.setOrderid(orderCreated.getId());
        ic.setInvoiceamount((double) r.nextInt(10000));

        logger.info("Invoice created with id {} for order with id {}", ic.getId(), orderCreated.getId());
        rabbitTemplate.convertAndSend(Config.TOPIC_INVOICES, "invoices.created", ic);

    }

}
