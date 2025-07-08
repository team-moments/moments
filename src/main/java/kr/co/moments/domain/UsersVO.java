package kr.co.moments.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class UsersVO{
    private int users_no;
    private String users_email;
    private String users_pwd;
    private String users_name;
    private String users_phone;
    private String refresh_token;
    private Timestamp refresh_token_expired_at;
}