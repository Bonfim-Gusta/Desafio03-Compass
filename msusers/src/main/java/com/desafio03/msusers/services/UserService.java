package com.desafio03.msusers.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.desafio03.msusers.entities.User;
import com.desafio03.msusers.exceptions.UsernameOrEmailExistsException;
import com.desafio03.msusers.exceptions.WrongPasswordToUpdateException;
import com.desafio03.msusers.infra.mqueue.SendNotify;
import com.desafio03.msusers.infra.repositories.UserRepository;
import com.desafio03.msusers.model.UsernameOperation;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SendNotify sendNotify;
    private final PasswordEncoder passwordEncoder;
    private final HttpServletRequest request;

    @Transactional
    public User saveUser(User user) {
        try {
            userRepository.save(user);
            sendNotify.sendNotify(new UsernameOperation(user.getUsername(), "CREATE"));
            return user;
        } catch (DataIntegrityViolationException e){
            throw new UsernameOrEmailExistsException("Username or email exists");
        }
    }

    @Transactional
    public User updatePassword(String oldPassword, String newPassword) {
        String token = request.getHeader("Authorization");
        String tokenDecodifiqued = JWT.decode(token.substring("Bearer ".length())).getSubject();

        User user = (User) userRepository.findByUsername(tokenDecodifiqued);
        if (!passwordEncoder.matches(oldPassword, user.getPassword())){
            throw new WrongPasswordToUpdateException("The actual password is wrong");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        sendNotify.sendNotify(new UsernameOperation(user.getUsername(), "UPDATE"));
        return user;
    }
}
