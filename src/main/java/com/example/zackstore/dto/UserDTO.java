package com.example.zackstore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long userId;
    private String username;
    private String email;
    private String address;
    private String role;
}