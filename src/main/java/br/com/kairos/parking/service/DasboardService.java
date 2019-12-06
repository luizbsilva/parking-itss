package br.com.kairos.parking.service;

import br.com.kairos.parking.dto.DashboardEstatisticaDia;
import br.com.kairos.parking.model.Estacionamento;
import br.com.kairos.parking.model.Patio;
import br.com.kairos.parking.repository.EstacionamentoRepository;
import br.com.kairos.parking.repository.PatioRepository;
import br.com.kairos.parking.resource.DashboardResource;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DasboardService {

    @Autowired
    private EstacionamentoRepository estacionamentoRepository;
    @Autowired
    private PatioRepository patioRepository;

    @Autowired
    private PatioService patioService;

    public DashboardEstatisticaDia porDia(Long codigoPatio) {

        Patio patio = patioService.buscarPatioPeloCodigo(codigoPatio);
        List<Estacionamento> vagasEstacionamento = estacionamentoRepository.findByPatioCodigoAndAndDataEntrada(patio.getCodigo(), LocalDate.now());

        List<Estacionamento> vagasEstacionamentoOcupadas = vagasEstacionamento
                .stream()
                .filter(c -> c.getHorarioSaida() != null)
                .collect(Collectors.toList());

        Long vagasDisponiveis = !vagasEstacionamento.isEmpty() ? patio.getNumeroDeVagas() - vagasEstacionamento.size() : patio.getNumeroDeVagas();
        Long vagasOcupadas = Long.parseLong(vagasEstacionamentoOcupadas.size()+"");
        Long totaDeVagas = patio.getNumeroDeVagas();

        return new DashboardEstatisticaDia(LocalDate.now(), totaDeVagas, vagasOcupadas, vagasDisponiveis);
    }
}
