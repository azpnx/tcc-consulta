package com.tcc.tccconsultas.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tcc.tccconsultas.enums.AppUserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ConsultaResponse {
    @JsonProperty("entity_id")
    private Integer entityId;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("professional_id")
    private String email;
    @JsonProperty("session_date")
    private String sessionDate;
    @JsonProperty("meet_id")
    private Long meetId;
    private String nome;
    private AppUserRoleEnum role;
}
