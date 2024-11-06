package com.desafio03.msusers.exceptions;

public class UsernameOrEmailExistsException extends RuntimeException {
    public UsernameOrEmailExistsException(String message) {
        super(message);
    }
}
