package br.com.kairos.parking.repository;

import br.com.kairos.parking.model.Permissao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
    
    public Page<Permissao> findByDescricaoContaining(String nome, Pageable pageable);
    
}
