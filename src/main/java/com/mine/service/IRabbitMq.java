package com.mine.service;

public interface IRabbitMq {

    void publishUserRegister(Object object) throws Exception;
}
