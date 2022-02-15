package com.example.restfularticlemanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExceptionResponse {

    private Integer status;
    private String message;
    private LocalDateTime localDateTime;
}
