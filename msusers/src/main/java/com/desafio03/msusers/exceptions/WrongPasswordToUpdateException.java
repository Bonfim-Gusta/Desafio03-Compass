package com.desafio03.msusers.exceptions;

public class WrongPasswordToUpdateException extends RuntimeException {
    public WrongPasswordToUpdateException(String message) {
        super(message);
    }
}
