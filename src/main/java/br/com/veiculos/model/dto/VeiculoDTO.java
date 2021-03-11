package br.com.veiculos.model.dto;

import br.com.veiculos.model.Veiculo;
import lombok.Data;

import java.util.Date;

@Data
public class VeiculoDTO {

    private String placa;
    private Double precoAnuncio;
    private Long ano;
    private Date dataCadastro;
    private String preco;
    private String modelo;
    private String marca;
    private Long marcaId;
    private Long modeloId;

    public VeiculoDTO(Veiculo veiculo){
        this.placa = veiculo.getPlaca();
        this.precoAnuncio = veiculo.getPrecoAnuncio();
        this.ano = veiculo.getAno();
        this.dataCadastro = veiculo.getDataCadastro();
        this.preco = Double.toString(veiculo.getPreco());
        this.modelo = veiculo.getModelo().getName();
        this.marca = veiculo.getModelo().getMarca().getName();
    }

    public Veiculo getVeiculo(){
        Veiculo v = new Veiculo();
        v.setPlaca(this.placa);
        v.setPrecoAnuncio(this.precoAnuncio);
        v.setAno(this.ano);
        v.setDataCadastro(this.dataCadastro);
        v.setPreco(Double.parseDouble(this.preco));
        v.getModelo().setName(this.modelo);
        v.getModelo().setFipeId(this.modeloId);
        v.getModelo().getMarca().setName(this.marca);
        v.getModelo().getMarca().setFipeId(this.marcaId);
        return v;
    }












}
