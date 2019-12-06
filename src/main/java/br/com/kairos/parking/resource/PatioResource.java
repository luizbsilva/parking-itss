package br.com.kairos.parking.resource;

import br.com.kairos.parking.event.RecursoCriadoEvent;
import br.com.kairos.parking.model.Patio;
import br.com.kairos.parking.repository.PatioRepository;
import br.com.kairos.parking.service.PatioService;
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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patios")
public class PatioResource {
    
    @Autowired
    private PatioRepository patioRepository;
    
    @Autowired
    private PatioService patioService;
    
    @Autowired
    private ApplicationEventPublisher publisher;
    
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PATIO') and #oauth2.hasScope('write')")
    public ResponseEntity<Patio> criar(@Valid @RequestBody final Patio patio, final HttpServletResponse response) {
        final Patio patiosSalvo = this.patioService.salvar(patio);
        this.publisher.publishEvent(new RecursoCriadoEvent(this, response, patiosSalvo.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(patiosSalvo);
    }
    
    @GetMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_PATIO') and #oauth2.hasScope('read')")
    public ResponseEntity<Patio> buscarPeloCodigo(@PathVariable final Long codigo) {
        final Optional<Patio> patio = this.patioRepository.findById(codigo);
        return patio.isPresent() ? ResponseEntity.ok(patio.get()) : ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_REMOVER_PATIO') and #oauth2.hasScope('write')")
    public void remover(@PathVariable final Long codigo) {
        this.patioRepository.deleteById(codigo);
    }
    
    @PutMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PATIO') and #oauth2.hasScope('write')")
    public ResponseEntity<Patio> atualizar(@PathVariable final Long codigo, @Valid @RequestBody final Patio patio) {
        final Patio patioSalvo = this.patioService.atualizar(codigo, patio);
        return ResponseEntity.ok(patioSalvo);
    }
    
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_PATIO')")
    public Page<Patio> pesquisar(@RequestParam(required = false, defaultValue = "%") final String nome, final Pageable pageable) {
        return this.patioRepository.findByDescricaoPatioContaining(nome, pageable);
    }

    @GetMapping("/buscar-todos")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_PATIO')")
    public List<Patio> buscarTodos() {
        return this.patioRepository.findAll();
    }
    
}
