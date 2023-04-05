package com.mine.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.mine.util.RabbitmqConstant.*;
@Configuration
public class ConfigRabbitMq {
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_USER);
    }


    @Bean
    Queue queue() {
        return new Queue(QUEUE_USER, false);
    }

    @Bean
    Binding bindingUserRegister() {
        return BindingBuilder.bind(queue()).to(topicExchange()).with(ROUTING_KEY_USER_REGISTER);
    }
}
