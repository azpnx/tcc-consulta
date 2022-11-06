package com.tcc.tccconsultas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {
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
}
