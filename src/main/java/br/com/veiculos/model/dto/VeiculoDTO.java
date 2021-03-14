package br.com.veiculos.model.dto;

import br.com.veiculos.model.Marca;
import br.com.veiculos.model.Modelo;
import br.com.veiculos.model.Veiculo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;

@Data
public class VeiculoDTO {

    private String placa;
    private Double precoAnuncio;
    private Long ano;
    private String preco;
    private Date dataCadastro;
    private String modelo;
    private String marca;
    @JsonIgnore
    private Long marcaId;
    @JsonIgnore
    private Long modeloId;

    public VeiculoDTO(String placa, Double precoAnuncio, Long ano, Double preco, Date dataCadastro,
                       String modelo, String marca) {
        this.placa = placa;
        this.precoAnuncio = precoAnuncio;
        this.ano = ano;
        this.dataCadastro = dataCadastro;
        this.preco = Double.toString(preco);
        this.modelo = modelo;
        this.marca = marca;
    }

    public VeiculoDTO(String placa, Long marcaId,Long modeloId, Double precoAnuncio, Long ano) {
        this.placa = placa;
        this.precoAnuncio = precoAnuncio;
        this.ano = ano;
        this.modeloId = modeloId;
        this.marcaId = marcaId;
    }

    public VeiculoDTO(Veiculo veiculo){
        this.placa = veiculo.getPlaca();
        this.precoAnuncio = veiculo.getPrecoAnuncio();
        this.ano = veiculo.getAno();
        this.dataCadastro = veiculo.getDataCadastro();
        this.preco = Double.toString(veiculo.getPreco());
        this.modelo = veiculo.getModelo().getName();
        this.marca = veiculo.getModelo().getMarca().getName();
    }

    public Veiculo getVeiculoWithNewModelAndNewMark() {
        Veiculo v = new Veiculo();
        Modelo d = new Modelo();
        Marca m = new Marca();
        v.setPlaca(this.placa);
        v.setPrecoAnuncio(this.precoAnuncio);
        v.setAno(this.ano);
        v.setDataCadastro(this.dataCadastro);
        String st = this.preco.replace("R$","");
        v.setPreco(Double.parseDouble(st.replace(",","")));
        m.setName(this.marca);
        m.setFipeId(this.marcaId);
        d.setMarca(m);
        d.setName(this.modelo);
        d.setFipeId(this.modeloId);
        v.setModelo(d);
        return v;
    }

    public Veiculo getVeiculoWithNewModelOnly() {
        Veiculo v = new Veiculo();
        v.setPlaca(this.placa);
        v.setPrecoAnuncio(this.precoAnuncio);
        v.setAno(this.ano);
        v.setDataCadastro(this.dataCadastro);
        String st = this.preco.replace("R$","");
        v.setPreco(Double.parseDouble(st.replace(",","")));
        return v;
    }



    public Veiculo getVeiculo() {
        Veiculo v = new Veiculo();
        v.setPlaca(this.placa);
        v.setPrecoAnuncio(this.precoAnuncio);
        v.setAno(this.ano);
        v.setDataCadastro(this.dataCadastro);
        String st = this.preco.replace("R$","");
        v.setPreco(Double.parseDouble(st.replace(",","")));
        return v;
    }














}
