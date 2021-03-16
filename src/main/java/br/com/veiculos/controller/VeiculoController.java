package br.com.veiculos.controller;

import java.util.List;

import br.com.veiculos.model.dto.VeiculoDTO;
import br.com.veiculos.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("v1/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping("/list")
    public ResponseEntity<List<VeiculoDTO>> obterTodos(){
        var listVeiculo = veiculoService.obterTodos();
        return ResponseEntity.ok(listVeiculo);
    }

    @GetMapping
    public ResponseEntity<Page<VeiculoDTO>> obterTodos(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        var pessoas = veiculoService.obterTodos(pageable);
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> obterPorId(@PathVariable Long id) {
        var pessoa = veiculoService.obterPorId(id);
        return ResponseEntity.ok(pessoa);
    }


    @GetMapping("/placa/{placa}")
    public ResponseEntity<VeiculoDTO> obterPorPlaca(@PathVariable String placa) {
        var pessoa = veiculoService.obterVeiculoPorPlaca(placa);
        return ResponseEntity.ok(pessoa);
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<Page<VeiculoDTO>> obterPorMarca(@PageableDefault(size = 10, page = 0) Pageable pageable,@PathVariable String marca) {
        var pessoas = veiculoService.obterVeiculosPorMarca(marca,pageable);
        return ResponseEntity.ok(pessoas);
    }


    @PostMapping
    public ResponseEntity<VeiculoDTO> salvar(@RequestBody VeiculoDTO veiculoDTO) {
        var pessoa = veiculoService.salva(veiculoDTO);
        var uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(pessoa);
    }


    @PutMapping
    public ResponseEntity<VeiculoDTO> editar(@RequestBody VeiculoDTO pessoaDTO) {
        var pessoa = veiculoService.editar(pessoaDTO);
        return ResponseEntity.ok(pessoa);
    }

    @DeleteMapping
    public ResponseEntity<VeiculoDTO> remover(@RequestBody VeiculoDTO veiculoDTO) {
        veiculoService.delete(veiculoDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
