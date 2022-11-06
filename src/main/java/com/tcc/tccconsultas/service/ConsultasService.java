package com.tcc.tccconsultas.service;

import com.tcc.tccconsultas.client.ConsultasClient;
import com.tcc.tccconsultas.model.Consulta;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultasService {

    @Autowired
    private ConsultasClient consultasClient;

    public List<Consulta> findAllByProfessionalEmail(String professionalEmail){
        String[] email = professionalEmail.split("@");
        List<Consulta> saida = consultasClient.findAllByProfessional(email[0], email[1]);
        return saida;
    }

    public List<Consulta> findAll(){
        List<Consulta> saida = consultasClient.findAll();
        return saida;
    }
}
