package br.com.veiculos.repository;

import br.com.veiculos.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo,Long> {
    List<Veiculo>findByPlacaContainingIgnoreCase(String placa);
    Page<Veiculo> findByModeloId(Long Id,Pageable pageable);
}
