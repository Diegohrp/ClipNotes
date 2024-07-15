package com.diegohrp.backend.dtos;

import org.springframework.http.HttpStatus;

public record Error(String error, String message) {
}
