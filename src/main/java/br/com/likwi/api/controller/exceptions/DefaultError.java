package br.com.likwi.api.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class DefaultError {

    private LocalDateTime localDateTime;

    private Integer status;

    private String error;

    private String path;
}
