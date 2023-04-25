package com.tcc.tccconsultas.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor
public class TokenResponse {
    private String token;
    private String chatToken;
}
