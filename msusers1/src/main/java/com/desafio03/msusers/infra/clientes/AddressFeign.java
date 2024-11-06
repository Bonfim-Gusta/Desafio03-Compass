package com.desafio03.msusers.infra.clientes;

import com.desafio03.msusers.web.dto.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(url = "https://viacep.com.br/ws/", name = "viacep")
public interface AddressFeign {
    @GetMapping("{cep}/json/")
    Optional<AddressResponse> getAddressByCep(@PathVariable("cep") String cep);
}
