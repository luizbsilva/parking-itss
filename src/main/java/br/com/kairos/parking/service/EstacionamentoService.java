package br.com.kairos.parking.service;

import br.com.kairos.parking.model.Estacionamento;
import br.com.kairos.parking.model.Patio;
import br.com.kairos.parking.repository.EstacionamentoRepository;
import br.com.kairos.parking.repository.PatioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class EstacionamentoService {

    @Autowired
    private EstacionamentoRepository estacionamentoRepository;

    @Autowired
    private PatioRepository patioRepository;


    public Estacionamento salvar(final Estacionamento estacionamento) {
        return this.estacionamentoRepository.save(estacionamento);
    }

    public Estacionamento atualizar(final Long codigo, final Estacionamento estacionamento) {
        final Estacionamento estacionamentoNovo = this.buscarEstacionamentoPeloCodigo(codigo);
        Patio patio = this.buscarPatioPeloCodigo(estacionamento.getPatio().getCodigo());


        LocalDateTime t1 = LocalDateTime.of(estacionamento.getDataEntrada(), estacionamento.getHorarioChegada());
        LocalDateTime t2 = LocalDateTime.of(LocalDate.now(), estacionamento.getHorarioSaida());

        BigDecimal minutos = new BigDecimal(ChronoUnit.MINUTES.between(t1, t2));
        BigDecimal horas = minutos.divide(new BigDecimal(60), MathContext.DECIMAL128);

        BeanUtils.copyProperties(estacionamento, estacionamentoNovo, "codigo");
        estacionamentoNovo.setTempoPermanencia(horas);
        estacionamentoNovo.setValorPeriodo(patio.getTaxaHora().multiply(horas));
        estacionamentoNovo.setPatio(patio);

        return this.estacionamentoRepository.save(estacionamentoNovo);
    }

    public Estacionamento buscarEstacionamentoPeloCodigo(final Long codigo) {
        final Optional<Estacionamento> estacionamento = this.estacionamentoRepository.findById(codigo);
        if (!estacionamento.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        return estacionamento.get();
    }

    public Patio buscarPatioPeloCodigo(final Long codigo) {

        final Optional<Patio> patio = this.patioRepository.findById(codigo);
        if (!patio.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        return patio.get();
    }

    public Long buscarTotalDeVagas(Long codigo) {
        Patio patio = buscarPatioPeloCodigo(codigo);
        List<Estacionamento> estacionamentosOcupados = this.estacionamentoRepository.findByPatioCodigoAndAndDataEntradaAndHorarioSaidaIsNull(codigo, LocalDate.now());
        if (!estacionamentosOcupados.isEmpty()) {
            return patio.getNumeroDeVagas() - estacionamentosOcupados.size();
        }
        return patio.getNumeroDeVagas();
    }
}
