package com.springjwtsecurityexample.token.model;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {
    private String token;
    private String refreshToken;
}
