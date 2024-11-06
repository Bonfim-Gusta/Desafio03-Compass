package com.desafio03.msusers.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queue.emmited-users-operations}")
    private String queueEmmitedUsersOperations;

    @Bean
    public Queue queueEmmitedUsersOperations(){
        return new Queue(queueEmmitedUsersOperations, true);
    }
}
