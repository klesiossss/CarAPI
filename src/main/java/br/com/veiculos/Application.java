package br.com.veiculos;

import br.com.veiculos.api.FipeAPI;
import br.com.veiculos.model.Veiculo;
import br.com.veiculos.model.dto.VeiculoDTO;
import br.com.veiculos.repository.VeiculoRepository;
import br.com.veiculos.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    VeiculoRepository veiculoRepository;

    @Autowired
    VeiculoService veiculoService;

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        String marca = "FIAT";

        System.out.println("==============\n\n\n");
        Pageable pageable = null;
        Page<Veiculo> p3 = veiculoRepository.findByModeloMarcaName(marca,pageable);
        p3.stream().map(VeiculoDTO::new).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("=======Lista all=======\n\n\n");
        veiculoRepository.findAll().forEach(System.out::println);

        long marcaV = 23;
        long modeloV = 934;
        long ano = 2001;
        String placa = "XXX-PPb";
        double precoAnuncio = 100;
        FipeAPI fipeAPI = new FipeAPI();
        VeiculoDTO veiculoDTO = new VeiculoDTO(placa,marcaV,modeloV,precoAnuncio,ano);


        System.out.println("busca na api");
        System.out.println(veiculoService.salva(veiculoDTO));


    }

}