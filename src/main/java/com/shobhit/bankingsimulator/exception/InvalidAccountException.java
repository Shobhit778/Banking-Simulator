package com.shobhit.bankingsimulator.exception;

public class InvalidAccountException extends RuntimeException{
    public InvalidAccountException(String message){

        super(message);
    }
}
