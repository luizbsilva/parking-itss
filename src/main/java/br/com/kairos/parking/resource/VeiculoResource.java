package br.com.kairos.parking.resource;

import br.com.kairos.parking.event.RecursoCriadoEvent;
import br.com.kairos.parking.model.Veiculo;
import br.com.kairos.parking.repository.VeiculoRepository;
import br.com.kairos.parking.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
public class VeiculoResource {
    
    @Autowired
    private VeiculoRepository veiculoRepository;
    
    @Autowired
    private VeiculoService veiculoService;
    
    @Autowired
    private ApplicationEventPublisher publisher;
    
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_VEICULO') and #oauth2.hasScope('write')")
    public ResponseEntity<Veiculo> criar(@Valid @RequestBody final Veiculo veiculo, final HttpServletResponse response) {
        final Veiculo veiculoSalvo = this.veiculoService.salvar(veiculo);
        this.publisher.publishEvent(new RecursoCriadoEvent(this, response, veiculoSalvo.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoSalvo);
    }
    
    @GetMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_VEICULO') and #oauth2.hasScope('read')")
    public ResponseEntity<Veiculo> buscarPeloCodigo(@PathVariable final Long codigo) {
        final Optional<Veiculo> veiculo = this.veiculoRepository.findById(codigo);
        return veiculo.isPresent() ? ResponseEntity.ok(veiculo.get()) : ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_REMOVER_VEICULO') and #oauth2.hasScope('write')")
    public void remover(@PathVariable final Long codigo) {
        this.veiculoRepository.deleteById(codigo);
    }
    
    @PutMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_VEICULO') and #oauth2.hasScope('write')")
    public ResponseEntity<Veiculo> atualizar(@PathVariable final Long codigo, @Valid @RequestBody final Veiculo veiculo) {
        final Veiculo veiculoSalvo = this.veiculoService.atualizar(codigo, veiculo);
        return ResponseEntity.ok(veiculoSalvo);
    }
    
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_VEICULO')")
    public Page<Veiculo> pesquisar(@RequestParam(required = false, defaultValue = "%") final String nome, final Pageable pageable) {
        return this.veiculoRepository.findByModeloVeiculoContaining(nome, pageable);
    }
    
}
