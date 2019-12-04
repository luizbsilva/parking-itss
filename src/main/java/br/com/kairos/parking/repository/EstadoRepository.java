package br.com.kairos.parking.repository;

import br.com.kairos.parking.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
    
}
