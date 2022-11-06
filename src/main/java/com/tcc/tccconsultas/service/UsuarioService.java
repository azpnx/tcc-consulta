package com.tcc.tccconsultas.service;

import com.tcc.tccconsultas.client.UsuarioClient;
import com.tcc.tccconsultas.model.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioClient usuarioClient;

    public List<UsuarioResponse> findByProfessor(String email){
        return usuarioClient.findByProfessor(email);
    }

    public List<UsuarioResponse> findAllProfessionals(){
        return usuarioClient.findAllProfessionals();
    }

    public List<UsuarioResponse> getPacientes(){
        return usuarioClient.getAllPatients();
    }

    public List<UsuarioResponse> getAll(){
        return usuarioClient.getAll();
    }
}
