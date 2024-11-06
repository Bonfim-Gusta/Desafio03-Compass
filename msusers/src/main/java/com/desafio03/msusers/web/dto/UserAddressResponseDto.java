package com.desafio03.msusers.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAddressResponseDto {
    private String username;
    private String email;
    private AddressResponseTranslated address;
}
