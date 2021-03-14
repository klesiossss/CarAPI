package br.com.veiculos.service;

import br.com.veiculos.api.FipeAPI;
import br.com.veiculos.exceptions.DuplicatedResourceException;
import br.com.veiculos.exceptions.ResourceNotFoundException;
import br.com.veiculos.model.Marca;
import br.com.veiculos.model.Modelo;
import br.com.veiculos.model.Veiculo;
import br.com.veiculos.model.dto.VeiculoDTO;
import br.com.veiculos.repository.MarcaRepository;
import br.com.veiculos.repository.ModeloRepository;
import br.com.veiculos.repository.VeiculoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private ModeloRepository modeloRepository;
    @Autowired
    private MarcaRepository marcaRepository;


    public List<Veiculo> obterTodos() {
        var listPessoas = veiculoRepository.findAll();
        return listPessoas;
    }


    public Veiculo obterPorId(Long id) {
        var veiculo = veiculoRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return veiculo;
    }


    public VeiculoDTO obterVeiculoPorPlaca(String placa) {
        var veiculo = veiculoRepository.findByPlaca(placa)
                .orElseThrow(() -> new ResourceNotFoundException());
        return new VeiculoDTO(veiculo);
    }


    public Page<VeiculoDTO> obterVeiculosPorMarca(String marca, Pageable pageable) {
        var veiculos = veiculoRepository.findByModeloMarcaName(marca, pageable);
        var listVeiculos = veiculos.stream().map(VeiculoDTO::new)
                                        .collect(Collectors.toList());
        return new PageImpl<VeiculoDTO>(listVeiculos, pageable, veiculos.getTotalElements());
    }


    public VeiculoDTO salva(VeiculoDTO veiculoDTO) {

        FipeAPI fipeAPI = new FipeAPI();
        var fipe = fipeAPI.findFipePrice(veiculoDTO.getMarcaId(), veiculoDTO.getModeloId(),
                veiculoDTO.getAno()).orElseThrow(() -> new ResourceNotFoundException());

        var podeSalvar = veiculoRepository.findByPlaca(veiculoDTO.getPlaca()).isEmpty();
        var model = modeloRepository.findByFipeId(veiculoDTO.getModeloId());
        var mark = marcaRepository.findByFipeId(veiculoDTO.getMarcaId());
        if (podeSalvar) {
            veiculoDTO.setMarca(fipe.getMarca());
            veiculoDTO.setPreco(fipe.getPreco());
            veiculoDTO.setModelo(fipe.getVeiculo());
            veiculoDTO.setDataCadastro(new Date());

            if (model != null) {
                Veiculo v = veiculoDTO.getVeiculo();
                v.setModelo(model);
                veiculoRepository.save(v);
                System.out.println("ja tinha modelo");
            } else {
                if (mark == null) {
                    Veiculo v = veiculoDTO.getVeiculoWithNewModelAndNewMark();
                    marcaRepository.save(new Marca(veiculoDTO.getMarca(), veiculoDTO.getMarcaId()));
                    var marca = marcaRepository.findByFipeId(veiculoDTO.getMarcaId());
                    modeloRepository.save(new Modelo(veiculoDTO.getModelo(), veiculoDTO.getModeloId(), marca));
                    var modelo = modeloRepository.findByFipeId(veiculoDTO.getModeloId());
                    v.setModelo(modelo);
                    veiculoRepository.save(v);
                    System.out.println("nao tinha marca");
                } else {
                    Veiculo v = veiculoDTO.getVeiculoWithNewModelOnly();
                    modeloRepository.save(new Modelo(veiculoDTO.getModelo(), veiculoDTO.getModeloId(), mark));
                    var m = modeloRepository.findByFipeId(veiculoDTO.getModeloId());
                    v.setModelo(m);
                    veiculoRepository.save(v);
                    System.out.println("ja tinha marca");
                }
            }
        } else {
            throw new DuplicatedResourceException();
        }
        return veiculoDTO;


    }

}
