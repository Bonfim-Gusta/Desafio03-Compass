package com.desafio03.notify.infra.mqueue;

import com.desafio03.notify.UsernameOperation;
import com.desafio03.notify.infra.repository.NotifyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
@Service
public class RegisterOrUpdatedEmited {
    private final NotifyRepository notifyRepository;

    public RegisterOrUpdatedEmited(NotifyRepository notifyRepository) {
        this.notifyRepository = notifyRepository;
    }

    @RabbitListener(queues = "${mq.queue.emmited-users-operations}")
    public void receiveRegisterOrUpdate(@Payload String payload) throws JsonProcessingException {
        UsernameOperation usernameOperation = convertIntoObject(payload);
        log.info(payload);
        notifyRepository.save(usernameOperation);
    }

    private UsernameOperation convertIntoObject(String payload) {
        Gson json = new Gson();
        return json.fromJson(payload, UsernameOperation.class);
    }
}
