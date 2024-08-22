package com.rahul.userservicev1.exceptions;

public class WrongPasswordException extends  Exception{
    public WrongPasswordException(String message){
        super(message);
    }
}
