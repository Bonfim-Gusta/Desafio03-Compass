package com.desafio03.msusers.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdatePasswordDto {
    private String oldPassword;
    private String newPassword;
}
