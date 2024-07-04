package com.phoenix.signal.controller.platform.exception;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail {
    private String errorCode;
    private String message;
    private String detail;
    private String path;
    private LocalDateTime timestamp;
}
