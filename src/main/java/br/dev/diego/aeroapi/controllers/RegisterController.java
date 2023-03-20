package br.dev.diego.aeroapi.controllers;

import br.dev.diego.aeroapi.controllers.records.RegisterInsert;
import br.dev.diego.aeroapi.services.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/registers")
public class RegisterController {

    private RegisterService service;

    @PostMapping("/upload-register-data")
    public ResponseEntity<?> uploadRegisterDataBatch(@RequestBody List<RegisterInsert> records) {
        service.saveAllJdbcBatchCallable(records);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of("Message", "Registers data uploaded and saved to database successfully"));
    }

}
