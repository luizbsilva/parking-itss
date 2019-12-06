package br.com.kairos.parking.repository;

import br.com.kairos.parking.model.Estacionamento;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Long> {

    Optional<Estacionamento> findByPlacaVeiculoAndDataEntradaAndHorarioSaidaIsNull(String placaVeiculo, LocalDate dataEntrada);

    List<Estacionamento> findByPatioCodigoAndAndDataEntradaAndHorarioSaidaIsNull(Long codigoPatio, LocalDate dataEntrada);

    List<Estacionamento> findByPatioCodigoAndAndDataEntrada(Long codigoPatio, LocalDate dataEntrada);


}
