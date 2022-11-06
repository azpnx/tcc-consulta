package com.tcc.tccconsultas.model;

import com.tcc.tccconsultas.enums.AppUserRoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioResponse {
    private String email;
    private String nome;
    private AppUserRoleEnum role;
}
