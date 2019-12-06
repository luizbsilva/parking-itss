package br.com.kairos.parking.resource;

import br.com.kairos.parking.dto.EstacionamentoFiltro;
import br.com.kairos.parking.event.RecursoCriadoEvent;
import br.com.kairos.parking.model.Estacionamento;
import br.com.kairos.parking.model.Patio;
import br.com.kairos.parking.repository.EstacionamentoRepository;
import br.com.kairos.parking.service.EstacionamentoService;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estacioinamento")
public class EstacionamentoResource {
    
    @Autowired
    private EstacionamentoRepository estacionamentoRepository;
    
  @Autowired
    private EstacionamentoService estacionamentoService;

    @Autowired
    private ApplicationEventPublisher publisher;
    
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_ESTACIONAMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<Estacionamento> criar(@Valid @RequestBody final Estacionamento estacionamento, final HttpServletResponse response) {
        final Estacionamento estacionamentoSalvo = this.estacionamentoService.salvar(estacionamento);
        this.publisher.publishEvent(new RecursoCriadoEvent(this, response, estacionamentoSalvo.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(estacionamentoSalvo);
    }

    @GetMapping("/vagas-disponiveis/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_ESTACIONAMENTO') and #oauth2.hasScope('read')")
    public Long buscarTotalDeVagas(@PathVariable final Long codigo) {
        return this.estacionamentoService.buscarTotalDeVagas(codigo);
    }

    @GetMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_ESTACIONAMENTO') and #oauth2.hasScope('read')")
    public ResponseEntity<Estacionamento> buscarPeloCodigo(@PathVariable final Long codigo) {
        final Optional<Estacionamento> estacionamento = this.estacionamentoRepository.findById(codigo);
        return estacionamento.isPresent() ? ResponseEntity.ok(estacionamento.get()) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_REMOVER_ESTACIONAMENTO') and #oauth2.hasScope('write')")
    public void remover(@PathVariable final Long codigo) {
        this.estacionamentoRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_ESTACIONAMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<Estacionamento> atualizar(@PathVariable final Long codigo, @Valid @RequestBody final Estacionamento estacionamento) {
        final Estacionamento veiculoSalvo = this.estacionamentoService.atualizar(codigo, estacionamento);
        return ResponseEntity.ok(veiculoSalvo);
    }
    
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_ESTACIONAMENTO') and #oauth2.hasScope('read')")
    public Estacionamento pesquisar(@RequestParam("placaVeiculo") final String placaVeiculo) {
        final Optional<Estacionamento> estacionamento = this.estacionamentoRepository.findByPlacaVeiculoAndDataEntradaAndHorarioSaidaIsNull(placaVeiculo, LocalDate.now());
        return estacionamento.isPresent() ? estacionamento.get() : null;
    }

    @PostMapping("/buscar-estacionamento")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_PATIO')")
    public List<Estacionamento> pesquisar(@Valid @RequestBody final EstacionamentoFiltro estacionamento, final HttpServletResponse response) {
        return this.estacionamentoRepository.findByPatioCodigoAndAndDataEntrada(estacionamento.getPatio().getCodigo(), estacionamento.getDataRelatorio());
    }

}
