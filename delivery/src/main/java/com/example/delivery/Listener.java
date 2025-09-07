package com.example.delivery;

import com.example.shop.InvoiceCreated;
import com.example.shop.OrderCreated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {

    private static final Logger logger = LoggerFactory.getLogger(Listener.class);

    @RabbitListener(queues = "#{anonymousOrdersQueue.name}")
    public void orderCreated(OrderCreated orderCreated) {
        logger.info("Received order with id {}, preparing delivery", orderCreated.getId());
    }

    @RabbitListener(queues = "#{anonymousInvoicesQueue.name}")
    public void invoiceCreated(InvoiceCreated invoiceCreated) {
        logger.info("Received invoice notification with id {} for order with id {}, completing delivery", invoiceCreated.getId(), invoiceCreated.getOrderid());
    }

}
