package com.desafio03.notify.controller;

import com.desafio03.notify.UsernameOperation;
import com.desafio03.notify.infra.repository.NotifyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotifyRepository notificationRepository;

    @GetMapping
    public List<UsernameOperation> getAll(){
        return notificationRepository.findAll();
    }
}
