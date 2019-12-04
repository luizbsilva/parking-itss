package br.com.kairos.parking.resource;

import br.com.kairos.parking.model.Estado;
import br.com.kairos.parking.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoResource {
    
    @Autowired
    private EstadoRepository estadoRepository;
    
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<Estado> listar() {
        return this.estadoRepository.findAll();
    }
}
