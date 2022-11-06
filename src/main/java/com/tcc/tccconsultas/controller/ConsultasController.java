package com.tcc.tccconsultas.controller;

import com.tcc.tccconsultas.facade.ConsultasFacade;
import feign.form.ContentType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ConsultasController {

    private final ConsultasFacade consultasFacade;

    @GetMapping(value = "/professional/{professionalEmail}")
    public ResponseEntity<?> getAllConsultas(@PathVariable("professionalEmail") String professionalEmail){
        return ResponseEntity.ok(consultasFacade.findAllByProfessionalEmail(professionalEmail));
    }

    @GetMapping(value = "/teacher/{email}")
    public ResponseEntity<?> getAllByProfessor(@PathVariable("email") String email){
        return ResponseEntity.ok(consultasFacade.findAllByProfessor(email));
    }
}
