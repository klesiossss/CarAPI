package br.com.veiculos.model;

import lombok.*;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Marca extends EntityBase implements Serializable {
    private static final long serialVersionUID = -4799176576879859723L;

    private String name;
    private Long fipeId;

}
