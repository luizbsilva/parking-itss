package br.com.kairos.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kairos.parking.model.Permissao;
import br.com.kairos.parking.model.PermissaoUsuario;
import br.com.kairos.parking.model.Usuario;

public interface PermissaoUsuarioRepository extends JpaRepository<PermissaoUsuario, Long> {
    
    PermissaoUsuario findByPermissaoAndUsuario(final Permissao permissao, final Usuario usuario);
    
}
