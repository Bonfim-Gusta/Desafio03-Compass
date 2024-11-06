package com.desafio03.msusers.web.controller;

import com.desafio03.msusers.services.AddressService;
import com.desafio03.msusers.web.dto.AddressRequest;
import com.desafio03.msusers.web.dto.AddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/address")
@RestController
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;
    @GetMapping("/consult")
    public ResponseEntity<AddressResponse> getAddress(@RequestBody AddressRequest request){
        AddressResponse addressResponse = addressService.executeRequest(request);
        return ResponseEntity.ok(addressResponse);
    }
}
