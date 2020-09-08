package me.monkey.gateway.controller;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class User {
    private Long id;
    private String username;
    private String phone;
    private String mail;
    private Date createDate;
}
