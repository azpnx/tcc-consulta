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

    @PostMapping(value = "/twilio/room")
    public ResponseEntity<?> criaSala(@PathVariable("nome") String nomeSala){
        return ResponseEntity.ok(consultasFacade.criaSalaTwilioVideo(nomeSala));
    }

    @PostMapping(value = "/twilio/token")
    public ResponseEntity<?> criaSala(@PathVariable("userId") String userId ,@PathVariable("roomSid") String roomSid){
        return ResponseEntity.ok(consultasFacade.geraToken(userId, roomSid));
    }
}
