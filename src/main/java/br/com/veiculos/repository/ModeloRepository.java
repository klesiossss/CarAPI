package br.com.veiculos.repository;

import br.com.veiculos.model.Modelo;
import org.dom4j.rule.Mode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModeloRepository extends JpaRepository<Modelo,Long> {
    Modelo findByFipeId(Long fipeId);
}
