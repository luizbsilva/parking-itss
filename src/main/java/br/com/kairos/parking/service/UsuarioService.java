package br.com.kairos.parking.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.kairos.parking.model.Usuario;
import br.com.kairos.parking.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public Usuario salvar(final Usuario usuario) {
        final String senha = usuario.getSenha();
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setSenha(encoder.encode(senha));
        
        return this.usuarioRepository.save(usuario);
    }
    
    public Usuario atualizar(final Long codigo, final Usuario pessoa) {
        final Usuario pessoaSalva = this.buscarUsuarioPeloCodigo(codigo);
        
        BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
        return this.usuarioRepository.save(pessoaSalva);
    }
    
    public void atualizarPropriedadeAtivo(final Long codigo, final Boolean ativo) {
        final Usuario usuarioSalva = this.buscarUsuarioPeloCodigo(codigo);
        usuarioSalva.setAtivo(ativo);
        this.usuarioRepository.save(usuarioSalva);
    }
    
    public Usuario buscarUsuarioPeloCodigo(final Long codigo) {
        final Optional<Usuario> usuarioSalva = this.usuarioRepository.findById(codigo);
        if (!usuarioSalva.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        return usuarioSalva.get();
    }
    
}
