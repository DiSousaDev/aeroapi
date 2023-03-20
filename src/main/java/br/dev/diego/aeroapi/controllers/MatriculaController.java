package br.dev.diego.aeroapi.controllers;

import br.dev.diego.aeroapi.controllers.records.RegisterResponse;
import br.dev.diego.aeroapi.services.RegisterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class MatriculaController {

    private RegisterService service;

    @GetMapping("/matricula/{id}")
    public ResponseEntity<RegisterResponse> findByMatricula(@PathVariable String id) {
        log.info("findByMatricula {}", id);
        return ResponseEntity.ok(service.findByMatricula(id));
    }

}
