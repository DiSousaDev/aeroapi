package br.dev.diego.aeroapi.services.impl;

import br.dev.diego.aeroapi.controllers.records.RegisterInsert;
import br.dev.diego.aeroapi.controllers.records.RegisterResponse;
import br.dev.diego.aeroapi.entities.Register;
import br.dev.diego.aeroapi.repositories.RegisterRepository;
import br.dev.diego.aeroapi.services.RegisterService;
import br.dev.diego.aeroapi.services.exceptions.RegisterNotFoundException;
import br.dev.diego.aeroapi.services.util.ListUtil;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Service
public class RegisterServiceImpl implements RegisterService {

    HikariDataSource hikariDataSource;
    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    private RegisterRepository repository;

    @Override
    public RegisterResponse findByMatricula(String id) {
        Register register = repository.findByMarcaIgnoreCase(id).orElseThrow(() ->
                new RegisterNotFoundException("Register " + id + " não localizado."));
        RegisterResponse response = new RegisterResponse(register);
        log.info("Time [{}] Registro {} localizado. {}", LocalDateTime.now(), id, response);
        return response;
    }

    @Override
    public void saveAllJdbcBatchCallable(List<RegisterInsert> registers) {
        List<Register> list = registers.stream().map(registerInsert -> new Register(registerInsert)).collect(Collectors.toList());

        System.out.println("insert using jdbc batch, threading");
        ExecutorService executorService = Executors.newFixedThreadPool(hikariDataSource.getMaximumPoolSize());
        List<List<Register>> listOfBookSub = ListUtil.createSubList(list, batchSize);
        List<Callable<Void>> callables = listOfBookSub.stream().map(sublist ->
                (Callable<Void>) () -> {
                    saveAllJDBCBatch(sublist);
                    return null;
                }).collect(Collectors.toList());
        try {
            executorService.invokeAll(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void saveAllJDBCBatch(List<Register> list) {

        System.out.println("insert using jdbc batch");

        String query = """
                INSERT INTO %s 
                (
                    marca,
                    proprietario,
                    outros_proprietarios,
                    sg_uf,
                    cpf_cnpj,
                    nm_operador,
                    outros_operadores,
                    uf_operador,
                    cpf_cgc,
                    nr_cert_matricula,
                    nr_serie,
                    cd_categoria,
                    cd_tipo,
                    ds_modelo,
                    nm_fabricante,
                    cd_cls,
                    nr_pmd,
                    cd_tipo_icao,
                    nr_tripulacao_min,
                    nr_passageiros_max,
                    nr_assentos,
                    nr_ano_fabricacao,
                    dt_validade_cva,
                    dt_validade_ca,
                    dt_canc,
                    ds_motivo_canc,
                    cd_interdicao,
                    cd_marca_nac1,
                    cd_marca_nac2,
                    cd_marca_nac3,
                    cd_marca_estrangeira,
                    ds_gravame,
                    dt_matricula,
                    ultima_atualizacao
                ) VALUES 
                (
                    ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?
                )
                """;

        String sql = String.format(
                query,
                Register.class.getAnnotation(Table.class).name()
        );
        try (Connection connection = hikariDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            int counter = 0;
            for (Register register : list) {
                statement.clearParameters();
                statement.setString(1, register.getMarca());
                statement.setString(2, register.getProprietario());
                statement.setString(3, register.getOutrosProprietarios());
                statement.setString(4, register.getSgUf());
                statement.setString(5, register.getCpfCnpj());
                statement.setString(6, register.getNmOperador());
                statement.setString(7, register.getOutrosOperadores());
                statement.setString(8, register.getUfOperador());
                statement.setString(9, register.getCpfCgc());
                statement.setString(10, register.getNrCertMatricula());
                statement.setString(11, register.getNrSerie());
                statement.setString(12, register.getCdCategoria());
                statement.setString(13, register.getCdTipo());
                statement.setString(14, register.getDsModelo());
                statement.setString(15, register.getNmFabricante());
                statement.setString(16, register.getCdCls());
                statement.setString(17, register.getNrPmd());
                statement.setString(18, register.getCdTipoIcao());
                statement.setString(19, register.getNrTripulacaoMin());
                statement.setString(20, register.getNrPassageirosMax());
                statement.setString(21, register.getNrAssentos());
                statement.setString(22, register.getNrAnoFabricacao());
                statement.setString(23, register.getDtValidadeCva().toString());
                statement.setString(24, register.getDtValidadeCa().toString());
                statement.setString(25, register.getDtCanc());
                statement.setString(26, register.getDsMotivoCanc());
                statement.setString(27, register.getCdInterdicao());
                statement.setString(28, register.getCdMarcaNac1());
                statement.setString(29, register.getCdMarcaNac2());
                statement.setString(30, register.getCdMarcaNac3());
                statement.setString(31, register.getCdMarcaEstrangeira());
                statement.setString(32, register.getDsGravame());
                statement.setString(33, register.getDtMatricula().toString());
                statement.setString(34, register.getUltimaAtualizacao().toString());
                statement.addBatch();
                if ((counter + 1) % batchSize == 0 || (counter + 1) == list.size()) {
                    statement.executeBatch();
                    statement.clearBatch();
                }
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



