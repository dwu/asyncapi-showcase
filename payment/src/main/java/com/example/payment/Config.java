package com.example.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    public static final String TOPIC_ORDERS = "orders";
    public static final String TOPIC_INVOICES = "invoices";

    @Bean
    public TopicExchange orders() {
        return new TopicExchange(TOPIC_ORDERS);
    }

    @Bean
    public TopicExchange invoices() {
        return new TopicExchange(TOPIC_INVOICES);
    }

    @Bean
    public Queue anonymousOrdersQueue() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding(Queue anonymousOrderQueue, @Qualifier("orders") TopicExchange exchange) {
        return BindingBuilder.bind(anonymousOrderQueue).to(exchange).with("orders.*");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter messageConverter(ObjectMapper jsonMapper) {
        return new Jackson2JsonMessageConverter(jsonMapper);
    }

}
