package br.com.kairos.parking.resource;

import br.com.kairos.parking.event.RecursoCriadoEvent;
import br.com.kairos.parking.model.Usuario;
import br.com.kairos.parking.repository.UsuarioRepository;
import br.com.kairos.parking.service.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
	public ResponseEntity<Usuario> criar(@Valid @RequestBody final Usuario formulario,
										 final HttpServletResponse response) {
		final Usuario usuarioSalva = this.usuarioService.salvar(formulario);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalva);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<Usuario> buscarPeloCodigo(@PathVariable final Long codigo) {
		final Optional<Usuario> usuario = this.usuarioRepository.findById(codigo);
		return usuario.isPresent() ? ResponseEntity.ok(usuario.get()) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_USUARIO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable final Long codigo) {
		this.usuarioRepository.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
	public ResponseEntity<Usuario> atualizar(@PathVariable final Long codigo,
			@Valid @RequestBody final Usuario formulario) {
		final Usuario usuarioSalva = this.usuarioService.atualizar(codigo, formulario);
		return ResponseEntity.ok(usuarioSalva);
	}

	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
	public void atualizarPropriedadeAtivo(@PathVariable final Long codigo, @RequestBody final Boolean ativo) {
		this.usuarioService.atualizarPropriedadeAtivo(codigo, ativo);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO')")
	public Page<Usuario> pesquisar(@RequestParam(required = false, defaultValue = "%") final String nome,
			final Pageable pageable) {
		return this.usuarioRepository.findAll(pageable);
	}

}
