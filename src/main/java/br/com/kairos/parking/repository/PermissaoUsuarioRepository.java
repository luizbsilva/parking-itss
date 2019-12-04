package br.com.kairos.parking.repository;

import br.com.kairos.parking.model.Permissao;
import br.com.kairos.parking.model.PermissaoUsuario;
import br.com.kairos.parking.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoUsuarioRepository extends JpaRepository<PermissaoUsuario, Long> {
    
    PermissaoUsuario findByPermissaoAndUsuario(final Permissao permissao, final Usuario usuario);
    
}
