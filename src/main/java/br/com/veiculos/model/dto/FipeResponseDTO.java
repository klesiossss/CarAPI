package br.com.veiculos.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FipeResponseDTO {
     private String preco;
     private String marca;
     private String veiculo;
}
