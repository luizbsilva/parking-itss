package br.com.kairos.parking.service;

import br.com.kairos.parking.model.Patio;
import br.com.kairos.parking.repository.PatioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatioService {
    
    @Autowired
    private PatioRepository patioRepository;

    public Patio salvar(final Patio patio) {
        return this.patioRepository.save(patio);
    }
    
    public Patio atualizar(final Long codigo, final Patio patio) {
        final Patio novoPatio = this.buscarPatioPeloCodigo(codigo);

        BeanUtils.copyProperties(patio, novoPatio, "codigo");
        return this.patioRepository.save(novoPatio);
    }

    public Patio buscarPatioPeloCodigo(final Long codigo) {
        final Optional<Patio> patio = this.patioRepository.findById(codigo);
        if (!patio.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        return patio.get();
    }
    
}
