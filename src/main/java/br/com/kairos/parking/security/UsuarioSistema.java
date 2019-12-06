package br.com.kairos.parking.security;

import br.com.kairos.parking.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UsuarioSistema extends User {
    
    private static final long serialVersionUID = 1L;
    
    private final Usuario usuario;
    
    public UsuarioSistema(final Usuario usuario, final Collection<? extends GrantedAuthority> authorities) {
        super(usuario.getEmail(), usuario.getSenha(), authorities);
        this.usuario = usuario;
    }
    
    public Usuario getUsuario() {
        return this.usuario;
    }
    
}
