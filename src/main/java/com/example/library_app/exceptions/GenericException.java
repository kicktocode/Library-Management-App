package com.example.library_app.exceptions;

import com.example.library_app.dto.ErrorCode;
import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
public class GenericException extends RuntimeException{
    private HttpStatus httpStatus;
    private ErrorCode errorCode;
    private String errorMessage;
}
