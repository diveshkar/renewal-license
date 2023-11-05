package com.finalproject.mvpass.response;

public class ErrorHandle extends RuntimeException{
    public ErrorHandle(String message){
        super(message);
    }
}
