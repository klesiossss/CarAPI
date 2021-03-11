package br.com.veiculos.service;

import br.com.veiculos.api.FipeAPI;
import br.com.veiculos.exceptions.ResourceNotFoundException;
import br.com.veiculos.model.Veiculo;
import br.com.veiculos.model.dto.VeiculoDTO;
import br.com.veiculos.repository.VeiculoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public class VeiculoService {

    private  final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }


    public List<Veiculo> obterTodos(){
        var listPessoas = veiculoRepository.findAll();
        return listPessoas;
    }

    public List<Veiculo> obterVeiculoPorPlaca(String placa){
        var veiculos = veiculoRepository.findByPlacaContainingIgnoreCase(placa);
        return veiculos;
    }

    public Page<Veiculo> obterVeiculosPorMarca(Long modeloId,Pageable pageable){
        var pagePessoas = veiculoRepository.findByModeloId(modeloId,pageable);
        var listPessoas = pagePessoas.getContent();
        return new PageImpl<Veiculo>(listPessoas, pageable, pagePessoas.getTotalElements());
    }


    public Veiculo obterPorId(Long id){
        var veiculo = veiculoRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return veiculo;
    }

    public VeiculoDTO salva(VeiculoDTO veiculo){
        FipeAPI fipeAPI = new FipeAPI();

        var  fipe = fipeAPI.findFipePrice(veiculo.getMarcaId(), veiculo.getModeloId(), veiculo.getAno()).orElseThrow(()->new ResourceNotFoundException());
        veiculo.setMarca(fipe.getMarca());
        veiculo.setPreco(fipe.getPreco());
        veiculo.setModelo(fipe.getVeiculo());
        veiculo.setDataCadastro(new Date());
        Veiculo v = veiculo.getVeiculo();
        veiculoRepository.save(v);

        return veiculo;
    }




}
