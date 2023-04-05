package com.mine.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import static com.mine.util.RabbitmqConstant.*;
@Service
public class RabbitMqImpl implements IRabbitMq{
    RabbitTemplate rabbitTemplate;
    public RabbitMqImpl(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }
    @Override
    public void publishUserRegister(Object object) throws Exception {
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_USER, ROUTING_KEY_USER_REGISTER, object);
    }
}
