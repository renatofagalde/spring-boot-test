package br.com.likwi.api.controller.response;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;

    private String name;

    private String email;

    private String password;

}
