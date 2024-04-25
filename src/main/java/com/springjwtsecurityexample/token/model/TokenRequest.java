package com.springjwtsecurityexample.token.model;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TokenRequest {
    private String token;
}
