package br.dev.diego.aeroapi.services;

import br.dev.diego.aeroapi.controllers.records.RegisterInsert;
import br.dev.diego.aeroapi.controllers.records.RegisterResponse;

import java.util.List;

public interface RegisterService {

    void saveAllJdbcBatchCallable(List<RegisterInsert> registers);

    RegisterResponse findByMatricula(String id);

}
