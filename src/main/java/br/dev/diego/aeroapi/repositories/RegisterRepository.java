package br.dev.diego.aeroapi.repositories;

import br.dev.diego.aeroapi.entities.Register;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterRepository extends JpaRepository<Register, String> {

    Optional<Register> findByMarcaIgnoreCase(String marca);

}
