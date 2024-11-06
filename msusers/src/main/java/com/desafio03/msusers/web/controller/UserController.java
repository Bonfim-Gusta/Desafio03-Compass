package com.desafio03.msusers.web.controller;

import com.desafio03.msusers.entities.User;
import com.desafio03.msusers.services.AddressService;
import com.desafio03.msusers.services.UserService;
import com.desafio03.msusers.web.dto.AddressRequest;
import com.desafio03.msusers.web.dto.AddressResponse;
import com.desafio03.msusers.web.dto.UserAddressResponseDto;
import com.desafio03.msusers.web.dto.UserUpdatePasswordDto;
import com.desafio03.msusers.web.dto.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AddressService addressService;

    @PostMapping("/register")
    public ResponseEntity<UserAddressResponseDto> saveUser(@RequestBody @Valid User request){
        String encryptedPassword = new BCryptPasswordEncoder().encode(request.getPassword());
        request.setPassword(encryptedPassword);

        AddressResponse addressResponse = addressService.executeRequest(new AddressRequest(request.getCep()));
        User user = userService.saveUser(request);
        return ResponseEntity.ok(UserMapper.toDto(user, addressResponse));
    }

    @PutMapping("/update-password")
    public ResponseEntity<User> updatePassword(@RequestBody UserUpdatePasswordDto dto){
        userService.updatePassword(dto.getOldPassword(), dto.getNewPassword());
        return ResponseEntity.ok().build();
    }
}
