package com.tcc.tccconsultas.facade;

import com.tcc.tccconsultas.controller.response.ConsultaResponse;
import com.tcc.tccconsultas.mapper.ConsultaMapper;
import com.tcc.tccconsultas.model.Consulta;
import com.tcc.tccconsultas.model.UsuarioResponse;
import com.tcc.tccconsultas.service.ConsultasService;
import com.tcc.tccconsultas.service.UsuarioService;
import lombok.NoArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class ConsultasFacade {

    @Autowired
    private ConsultasService consultasService;

    @Autowired
    private UsuarioService usuarioService;

    public List<ConsultaResponse> findAllByProfessionalEmail(String professionalEmail){
        List<Consulta> consultas = consultasService.findAllByProfessionalEmail(professionalEmail);
        List<UsuarioResponse> usuarios = usuarioService.findAllProfessionals();
        List<UsuarioResponse> pacientes = usuarioService.getPacientes();
        return Mappers.getMapper(ConsultaMapper.class).toConsultasResponse(consultas, usuarios, pacientes);
    }

    public List<ConsultaResponse> findAllByProfessor(String email){
        List<Consulta> consultas = consultasService.findAll();
        List<UsuarioResponse> usuarios = usuarioService.findByProfessor(email);
        List<UsuarioResponse> pacientes = usuarioService.getPacientes();
        return Mappers.getMapper(ConsultaMapper.class).toConsultasResponse(consultas, usuarios, pacientes);
    }
}
