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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VeiculoService {
    private final VeiculoRepository veiculoRepository;
    private final ModeloRepository modeloRepository;
    private final MarcaRepository marcaRepository;

    public VeiculoService(VeiculoRepository veiculoRepository,
                          ModeloRepository modeloRepository,
                          MarcaRepository marcaRepository) {
        this.veiculoRepository = veiculoRepository;
        this.modeloRepository = modeloRepository;
        this.marcaRepository = marcaRepository;
    }


    public List<VeiculoDTO> obterTodos() {
        var listVeiculos = veiculoRepository.findAll();
        return listVeiculos.stream().map(VeiculoDTO::new).collect(Collectors.toList());
    }

    public Page<VeiculoDTO> obterTodos(Pageable pageable) {
        var listVeiculos = veiculoRepository.findAll(pageable);
        var veiculos = listVeiculos.getContent().stream()
                .map(VeiculoDTO::new).collect(Collectors.toList());
        return new PageImpl(veiculos, pageable, listVeiculos.getTotalElements());
    }


    public VeiculoDTO obterPorId(Long id) {
        var veiculo = veiculoRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return new VeiculoDTO(veiculo);
    }


    public VeiculoDTO obterVeiculoPorPlaca(String placa) {
        var veiculo = veiculoRepository.findByPlaca(placa)
                .orElseThrow(() -> new ResourceNotFoundException());
        return new VeiculoDTO(veiculo);
    }


    public Page<VeiculoDTO> obterVeiculosPorMarca(String marca, Pageable pageable) {
        var veiculos = veiculoRepository.findByModeloMarcaNameContainingIgnoreCase(marca, pageable);
        var listVeiculos = veiculos.stream().map(VeiculoDTO::new)
                .collect(Collectors.toList());
        return new PageImpl<VeiculoDTO>(listVeiculos, pageable, veiculos.getTotalElements());
    }


    public VeiculoDTO salva(VeiculoDTO veiculoDTO) {

        System.out.println("salvando...");
        System.out.println(veiculoDTO);
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
            veiculoDTO.setDataCadastro(LocalDate.now());

            if (model != null) {
                Veiculo v = veiculoDTO.getVeiculo();
                v.setModelo(model);
                v.setCriadoEm(LocalDateTime.now());
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
                    v.setCriadoEm(LocalDateTime.now());
                    veiculoRepository.save(v);
                    System.out.println("nao tinha marca");
                } else {
                    Veiculo v = veiculoDTO.getVeiculoWithNewModelOnly();
                    mark.setCriadoEm(LocalDateTime.now());
                    modeloRepository.save(new Modelo(veiculoDTO.getModelo(), veiculoDTO.getModeloId(), mark));
                    var m = modeloRepository.findByFipeId(veiculoDTO.getModeloId());
                    m.setCriadoEm(LocalDateTime.now());
                    v.setModelo(m);
                    v.setCriadoEm(LocalDateTime.now());
                    veiculoRepository.save(v);

                    System.out.println("ja tinha marca");
                }
            }
        } else {
            throw new DuplicatedResourceException();
        }
        return veiculoDTO;
    }


    public VeiculoDTO editar(VeiculoDTO veiculoDTO) {
        Veiculo vehicle = veiculoDTO.getVeiculo();
        var podeEditar = !veiculoRepository.findByPlaca(vehicle.getPlaca()).isEmpty();
        if (podeEditar) {
            vehicle.setAtualizadoEm(LocalDateTime.now());
            veiculoRepository.save(vehicle);
            return veiculoDTO;
        } else throw new ResourceNotFoundException();
    }

    public void delete(VeiculoDTO veiculoDTO) {

        var podeDeletar = veiculoRepository.findById(veiculoDTO.getId()).isPresent();
        if (podeDeletar) {
            veiculoRepository.deleteById(veiculoDTO.getId());
        } else {
            throw new ResourceNotFoundException();
        }
    }

}
