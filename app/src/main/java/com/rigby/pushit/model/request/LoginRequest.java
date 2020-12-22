package com.rigby.pushit.model.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginRequest implements Serializable {

    private String email;
    private String password;
}