package com.tcc.tccconsultas.client;

import com.tcc.tccconsultas.model.UsuarioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "psi-user", path = "")
public interface UsuarioClient {

    @GetMapping("/findByTeacher")
    List<UsuarioResponse> findByProfessor(@RequestParam String professor);

    @GetMapping("/allProfessionals")
    List<UsuarioResponse> findAllProfessionals();

    @GetMapping("/allPatients")
    List<UsuarioResponse> getAllPatients();

    @GetMapping("/all")
    List<UsuarioResponse> getAll();
}
