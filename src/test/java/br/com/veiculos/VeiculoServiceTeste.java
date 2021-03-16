package br.com.veiculos;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import br.com.veiculos.model.Marca;
import br.com.veiculos.model.Modelo;
import br.com.veiculos.model.Veiculo;
import br.com.veiculos.model.dto.VeiculoDTO;
import br.com.veiculos.repository.MarcaRepository;
import br.com.veiculos.repository.ModeloRepository;
import br.com.veiculos.repository.VeiculoRepository;
import br.com.veiculos.service.VeiculoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.veiculos.exceptions.BusinessException;
import br.com.veiculos.exceptions.DuplicatedResourceException;
import br.com.veiculos.exceptions.ResourceNotFoundException;


@SpringBootTest
public class VeiculoServiceTeste {

    @Mock
    private VeiculoRepository veiculoRepository;
    @Mock
    private ModeloRepository modeloRepository;
    @Mock
    private MarcaRepository marcaRepository;

    @InjectMocks
    private VeiculoService veiculoService;

    private static Veiculo veiculo;
    private static VeiculoDTO veiculoDTO;

    @BeforeAll
    static void beforeAll() {
        veiculo = new Veiculo();
        Marca marca = new Marca("Fiat", 321L);
        Modelo modelo = new Modelo("UNO",34L,marca);
        LocalDate date = LocalDate.now();
        Veiculo veiculo = new Veiculo("XXX-XXX",133.4,2014L,333.2, date,modelo);
        VeiculoDTO veiculoDTO = new VeiculoDTO(veiculo);

    }

    @Test
    @DisplayName("Deve salvar os dados de um veiculo")
    void testSalvarVeiculo() {
        when(veiculoRepository.findByPlaca("XXX-XXX")).thenReturn(Optional.empty());
        when(veiculoRepository.save(any(Veiculo.class))).thenReturn(veiculo);

        Marca marca = new Marca("Chevrolet", 321L);
        Modelo modelo = new Modelo("AGILE",34L,marca);
        var novoVeiculo = new Veiculo();
        novoVeiculo.setPlaca("XXX-XXX");
        novoVeiculo.setAno(2014L);
        novoVeiculo.setPreco(22000.0);
        novoVeiculo.setPrecoAnuncio(300.0);
        novoVeiculo.setModelo(modelo);
        novoVeiculo.setDataCadastro(LocalDate.now());
        var v = new VeiculoDTO(novoVeiculo);
        v.setModeloId(6639L);
        v.setMarcaId(23L);


        var veiculoDTOSalvo = veiculoService.salva(v);

        assertAll(() -> {

            assertEquals(novoVeiculo.getPlaca(), veiculoDTOSalvo.getPlaca());
            assertEquals(novoVeiculo.getAno(), veiculoDTOSalvo.getAno());
            assertEquals(novoVeiculo.getDataCadastro(), veiculoDTOSalvo.getDataCadastro());
        });
    }


}
