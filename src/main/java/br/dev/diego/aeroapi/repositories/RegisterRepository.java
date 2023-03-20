package br.dev.diego.aeroapi.repositories;

import br.dev.diego.aeroapi.entities.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterRepository extends JpaRepository<Register, String> {

    Optional<Register> findByMarcaIgnoreCase(String marca);

}
