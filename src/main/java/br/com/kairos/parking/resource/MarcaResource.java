package br.com.kairos.parking.resource;

import br.com.kairos.parking.model.MarcaVeiculo;
import br.com.kairos.parking.repository.MarcaVeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/marcas")
public class MarcaResource {
    
    @Autowired
    private MarcaVeiculoRepository marcaVeiculoRepository;
    
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<MarcaVeiculo> listar() {
        return this.marcaVeiculoRepository.findAll();
    }
}
