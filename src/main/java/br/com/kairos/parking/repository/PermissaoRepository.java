package br.com.kairos.parking.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kairos.parking.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
    
    public Page<Permissao> findByDescricaoContaining(String nome, Pageable pageable);
    
}
