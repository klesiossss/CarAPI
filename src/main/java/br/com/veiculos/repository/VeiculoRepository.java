package br.com.veiculos.repository;

import br.com.veiculos.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo,Long> {
    Optional<Veiculo> findByPlaca(String placa);
    Page<Veiculo> findByModeloMarcaName(String marca, Pageable pageable);

}
