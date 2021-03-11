package br.com.veiculos.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@MappedSuperclass
public class EntityBase implements Serializable {
    private static final long serialVersionUID = -5458306207983701850L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected LocalDateTime criadoEm = LocalDateTime.now();
    protected LocalDateTime atualizadoEm;
    protected Boolean ativo = true;

}
