package br.com.kairos.parking.repository;

import br.com.kairos.parking.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    public Optional<Usuario> findByEmail(String email);
    
    public List<Usuario> findByPermissoesDescricao(String permissaoDescricao);
    
    public Page<Usuario> findByNomeContaining(String nome, Pageable pageable);
    
}
