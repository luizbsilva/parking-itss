package br.com.kairos.parking.repository.filter;

import br.com.kairos.parking.model.Usuario;

import java.util.List;

public class PermissoesFilter {
    
    private Usuario usuario;
    
    private List<PermissaoFilter> permissoes;
    
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(final Usuario usuario) {
        this.usuario = usuario;
    }
    
    public List<PermissaoFilter> getPermissoes() {
        return this.permissoes;
    }
    
    public void setPermissoes(final List<PermissaoFilter> permissoes) {
        this.permissoes = permissoes;
    }
    
}
