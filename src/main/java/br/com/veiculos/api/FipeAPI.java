package br.com.veiculos.api;

import br.com.veiculos.model.dto.FipeResponseDTO;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class FipeAPI {

    private final RestTemplate restTemplate = new RestTemplate();

    //http://fipeapi.appspot.com/api/1/carros/veiculo/21/4828/2013-1.json
    public Optional<FipeResponseDTO> findFipePrice(Long marcaId, Long modeloId, Long ano) {

        String url = "http://fipeapi.appspot.com/api/1/carros/veiculo/"+marcaId+"/"+modeloId+"/"+ano+"-1.json";

     return Optional.of(restTemplate.getForObject(url, FipeResponseDTO.class));

    }

}