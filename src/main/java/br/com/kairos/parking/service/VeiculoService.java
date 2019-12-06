package br.com.kairos.parking.service;

import br.com.kairos.parking.model.MarcaVeiculo;
import br.com.kairos.parking.model.Veiculo;
import br.com.kairos.parking.repository.MarcaVeiculoRepository;
import br.com.kairos.parking.repository.VeiculoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VeiculoService {
    
    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private MarcaVeiculoRepository marcaVeiculoRepository;
    
    public Veiculo salvar(final Veiculo veiculo) {
        return this.veiculoRepository.save(veiculo);
    }
    
    public Veiculo atualizar(final Long codigo, final Veiculo veiculo) {
        final Veiculo novoVeiculo = this.buscarPessoaPeloCodigo(codigo);

        BeanUtils.copyProperties(veiculo, novoVeiculo, "codigo");
        novoVeiculo.setMarcaVeiculo(this.buscarMarcaPeloCodigo(veiculo.getMarcaVeiculo().getCodigo()));
        return this.veiculoRepository.save(novoVeiculo);
    }

    public Veiculo buscarPessoaPeloCodigo(final Long codigo) {
        final Optional<Veiculo> veiculo = this.veiculoRepository.findById(codigo);
        if (!veiculo.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        return veiculo.get();
    }

    public MarcaVeiculo buscarMarcaPeloCodigo(final Long codigo) {

        final Optional<MarcaVeiculo> marcaVeiculo = this.marcaVeiculoRepository.findById(codigo);
        if (!marcaVeiculo.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        return marcaVeiculo.get();
    }
    
}
