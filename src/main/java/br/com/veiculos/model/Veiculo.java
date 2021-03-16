package br.com.veiculos.model;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
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
    private LocalDate dataCadastro;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Modelo modelo;

}
