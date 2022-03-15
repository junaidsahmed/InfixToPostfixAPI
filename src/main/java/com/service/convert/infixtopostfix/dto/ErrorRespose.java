package com.service.convert.infixtopostfix.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author Junaid Shakeel
 * @project Open Source
 */
@NoArgsConstructor
@Getter
@Setter
public class ErrorRespose {
    HttpStatus status;
    String errorMessage;
    LocalDateTime timestamp=LocalDateTime.now();

    public ErrorRespose(HttpStatus status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }
}
