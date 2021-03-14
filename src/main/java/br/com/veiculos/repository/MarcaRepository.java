package br.com.veiculos.repository;

import br.com.veiculos.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
    Marca findByFipeId(Long fipeId);
}
