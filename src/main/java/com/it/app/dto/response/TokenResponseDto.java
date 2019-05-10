package com.it.app.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Response Data Transfer Object class for tokens
 */
@Getter
@Setter
public class TokenResponseDto {
    private String token;
    private String type = "Bearer";

    public TokenResponseDto(String accessToken) {
        this.token = accessToken;
    }
}
