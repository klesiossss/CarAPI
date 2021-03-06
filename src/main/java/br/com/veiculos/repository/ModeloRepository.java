package br.com.veiculos.repository;

import br.com.veiculos.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo,Long> {
    Modelo findByFipeId(Long fipeId);
}
