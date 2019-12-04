package br.com.kairos.parking.resource;

import br.com.kairos.parking.model.Cidade;
import br.com.kairos.parking.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {
    
    @Autowired
    private CidadeRepository cidadeRepository;
    
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<Cidade> pesquisar(@RequestParam final Long estado) {
        return this.cidadeRepository.findByEstadoCodigo(estado);
    }
    
}
