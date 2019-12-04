package br.com.kairos.parking.service;

import br.com.kairos.parking.model.Pessoa;
import br.com.kairos.parking.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository pessoaRepository;
    
    public Pessoa salvar(final Pessoa pessoa) {
        pessoa.getContatos().forEach(c -> c.setPessoa(pessoa));
        return this.pessoaRepository.save(pessoa);
    }
    
    public Pessoa atualizar(final Long codigo, final Pessoa pessoa) {
        final Pessoa pessoaSalva = this.buscarPessoaPeloCodigo(codigo);
        
        pessoaSalva.getContatos().clear();
        pessoaSalva.getContatos().addAll(pessoa.getContatos());
        pessoaSalva.getContatos().forEach(c -> c.setPessoa(pessoaSalva));
        
        BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo", "contatos");
        return this.pessoaRepository.save(pessoaSalva);
    }
    
    public void atualizarPropriedadeAtivo(final Long codigo, final Boolean ativo) {
        final Pessoa pessoaSalva = this.buscarPessoaPeloCodigo(codigo);
        pessoaSalva.setAtivo(ativo);
        this.pessoaRepository.save(pessoaSalva);
    }
    
    public Pessoa buscarPessoaPeloCodigo(final Long codigo) {
        final Optional<Pessoa> pessoaSalva = this.pessoaRepository.findById(codigo);
        if (!pessoaSalva.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        return pessoaSalva.get();
    }
    
}
