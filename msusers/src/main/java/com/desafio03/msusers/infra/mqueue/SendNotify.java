package com.desafio03.msusers.infra.mqueue;

import com.desafio03.msusers.exceptions.IncompativeJsonStructureException;
import com.desafio03.msusers.model.UsernameOperation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SendNotify {
    private final RabbitTemplate rabbitTemplate;
    private final Queue queueEmmitedUsersOperations;

    public void sendNotify(UsernameOperation dados){
        String json = convertIntoJson(dados);
        rabbitTemplate.convertAndSend(queueEmmitedUsersOperations.getName(), json);
    }

    private String convertIntoJson(UsernameOperation dados) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            var json = mapper.writeValueAsString(dados);
            return json;
        } catch (JsonProcessingException e) {
            throw new IncompativeJsonStructureException("Error to converte json message");        }
    }
}
