package br.com.likwi.api.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;

    private String name;

    private String email;

    @JsonIgnore
    private String password;

}
