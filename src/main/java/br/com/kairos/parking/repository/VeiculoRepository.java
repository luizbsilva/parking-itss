package br.com.kairos.parking.repository;

import br.com.kairos.parking.model.Pessoa;
import br.com.kairos.parking.model.Veiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    
    Page<Veiculo> findByModeloVeiculoContaining(String nome, Pageable pageable);
    
}
