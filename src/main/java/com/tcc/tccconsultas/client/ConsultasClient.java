package com.tcc.tccconsultas.client;

import com.tcc.tccconsultas.model.Consulta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "consultas", url = "http://tcc-app-session-schedule.herokuapp.com/v1/")
public interface ConsultasClient {

    @GetMapping("/session/professional/{email}@{provedor}")
    List<Consulta> findAllByProfessional(@PathVariable("email") String professionalEmail, @PathVariable("provedor") String provedor);

    @GetMapping("/session/all")
    List<Consulta> findAll();
}
