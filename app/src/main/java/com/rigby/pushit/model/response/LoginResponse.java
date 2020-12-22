package com.rigby.pushit.model.response;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

@Data
public class LoginResponse implements Serializable {

    private Long id;
    private String token;
    private String email;
    private LocalDate birthday;
}