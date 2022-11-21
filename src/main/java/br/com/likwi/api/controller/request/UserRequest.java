package br.com.likwi.api.controller.request;

import lombok.Data;

@Data
public class UserRequest {

    private Long id;

    private String name;

    private String email;

    private String password;

}
