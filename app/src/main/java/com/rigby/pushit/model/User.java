package com.rigby.pushit.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {

    private String email;
    private String password;
    private String name;
}