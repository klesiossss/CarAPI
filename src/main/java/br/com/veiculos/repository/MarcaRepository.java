package br.com.veiculos.repository;

import br.com.veiculos.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    Marca findByFipeId(Long fipeId);
}
