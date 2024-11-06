package com.desafio03.msusers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class test {

    @GetMapping
    public ResponseEntity<String> teste(){
        return ResponseEntity.ok("teste");
    }
}
