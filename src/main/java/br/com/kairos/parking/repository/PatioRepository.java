package br.com.kairos.parking.repository;

import br.com.kairos.parking.model.Patio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatioRepository extends JpaRepository<Patio, Long> {
    
    Page<Patio> findByDescricaoPatioContaining(String nome, Pageable pageable);
    
}
