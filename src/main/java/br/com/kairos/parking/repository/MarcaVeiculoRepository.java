package br.com.kairos.parking.repository;

import br.com.kairos.parking.model.MarcaVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaVeiculoRepository extends JpaRepository<MarcaVeiculo, Long> {

    
}
