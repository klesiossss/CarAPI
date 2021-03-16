package br.com.veiculos;


import br.com.veiculos.model.Marca;
import br.com.veiculos.model.Modelo;
import br.com.veiculos.model.Veiculo;
import br.com.veiculos.repository.VeiculoRepository;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class VeiculoRepositoryTest{

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void createShouldPersistDate(){

        Marca marca = new Marca("asdd", 321L);
        Modelo modelo = new Modelo("as",34L,marca);

        LocalDate date = LocalDate.now();
        Veiculo veiculo = new Veiculo("XXX-XXC",133.4,2000L,333.2, date,modelo);
        this.veiculoRepository.save(veiculo);
        assertThat(veiculo.getId()).isNotNull();
        assertThat(veiculo.getPlaca()).isEqualTo("XXX-XXC");
        assertThat(veiculo.getAno()).isEqualTo(2000L);
    }
    @Test
    public void deleteShouldRemoveData(){
        Marca marca = new Marca("asdd", 321L);
        Modelo modelo = new Modelo("as",34L,marca);

        LocalDate date = LocalDate.now();
        Veiculo veiculo = new Veiculo("XXX-XXC",133.4,2000L,333.2, date,modelo);
        this.veiculoRepository.save(veiculo);
        veiculoRepository.delete(veiculo);
        assertThat(veiculoRepository.findById(veiculo.getId())).isEmpty();

    }

    @Test
    public void updateShouldChangeAndPersisteDate(){
        Marca marca = new Marca("asdd", 321L);
        Modelo modelo = new Modelo("as",34L,marca);

        LocalDate date = LocalDate.now();
        Veiculo veiculo = new Veiculo("XXX-XXC",133.4,2000L,333.2, date,modelo);
        this.veiculoRepository.save(veiculo);
        veiculo.setPlaca("XXX-AAA");
        veiculo.setAno(2001L);
         this.veiculoRepository.save(veiculo);
        var v = veiculoRepository.findById(veiculo.getId());
        assertThat(v.get().getPlaca()).isEqualTo("XXX-AAA");
        assertThat(v.get().getAno()).isEqualTo(2001L);

    }


    @Test
    public void findByModeloMarcaNameContainingIgnoreCaseShouldIgnoreCase(){
        Marca marca1 = new Marca("ASDF", 321L);
        Marca marca2 = new Marca("asdf", 321L);
        Modelo modelo1 = new Modelo("as",34L,marca1);
        Modelo modelo2 = new Modelo("as",34L,marca2);
        LocalDate date = LocalDate.now();
        Veiculo veiculo1 = new Veiculo("XXX-XXC",133.4,2000L,333.2, date,modelo1);
        Veiculo veiculo2 = new Veiculo("xxx-xxc",133.4,2000L,333.2, date,modelo2);
        this.veiculoRepository.save(veiculo1);
        this.veiculoRepository.save(veiculo2);
        Pageable pageable = null;
        var pv = veiculoRepository.findByModeloMarcaNameContainingIgnoreCase("asdf",pageable);
        assertThat(pv.getTotalElements()).isEqualTo(2);

    }



}
