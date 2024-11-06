package com.desafio03.msusers.services;

import com.desafio03.msusers.exceptions.CepNotFoundException;
import com.desafio03.msusers.infra.clientes.AddressFeign;
import com.desafio03.msusers.web.dto.AddressRequest;
import com.desafio03.msusers.web.dto.AddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AddressService {
    private final AddressFeign addressFeign;
    public AddressResponse executeRequest(AddressRequest cepRequest){
        Optional<AddressResponse> address = addressFeign.getAddressByCep(cepRequest.getCep());
        if (address.get().getCep() == null){
            throw new CepNotFoundException(String.format("Cep %s was not found or not exists", cepRequest.getCep()));
        }
        return address.get();
    }
}
