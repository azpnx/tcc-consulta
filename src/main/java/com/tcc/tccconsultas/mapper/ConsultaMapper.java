package com.tcc.tccconsultas.mapper;

import com.tcc.tccconsultas.controller.response.ConsultaResponse;
import com.tcc.tccconsultas.model.Consulta;
import com.tcc.tccconsultas.model.UsuarioResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ConsultaMapper {

    @Mapping(target = "email", source = "consulta.email")
    ConsultaResponse toConsultaResponse(Consulta consulta, UsuarioResponse usuarioResponse);

    default List<ConsultaResponse> toConsultasResponse(List<Consulta> consultas, List<UsuarioResponse> usuarioResponses){
        List<ConsultaResponse> consultaResponses = new ArrayList<>();

        HashMap<String, UsuarioResponse> usuarioHashMap = new HashMap<>();
        usuarioResponses.forEach(usuarioResponse -> usuarioHashMap.put(usuarioResponse.getEmail(), usuarioResponse));

        consultas.forEach(consulta -> consultaResponses.add(toConsultaResponse(consulta, usuarioHashMap.get(consulta.getEmail()))));
        consultaResponses.forEach(consultaResponse ->
                consultaResponse.setNomePaciente(usuarioHashMap.get(consultaResponse.getUserId()).getNome())
                );
        return consultaResponses;
    }
}
