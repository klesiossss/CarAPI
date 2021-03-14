package br.com.veiculos.model;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo extends EntityBase implements Serializable {
    private static final long serialVersionUID = -4134664146349751142L;

    private String placa;
    private Double precoAnuncio;
    private Long ano;
    private Double preco;
    private Date dataCadastro;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private Modelo modelo;

}
