package br.com.veiculos.model;


import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Modelo extends EntityBase implements Serializable {
    private static final long serialVersionUID = -5466066901098386692L;

    private String name;
    private Long fipeId;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Marca marca;

}
