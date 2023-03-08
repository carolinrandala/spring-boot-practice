package com.sda.study.springbootpractice.handlers.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Model for Error Response
 * (Will return client readable error)
 */
@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private List<String> details;
}
