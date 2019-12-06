package br.com.kairos.parking.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "patio")
public class Patio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotNull
    @Column(name = "descricao")
    private String descricaoPatio;

    @NotNull
    @Column(name = "numero_vagas")
    private Long numeroDeVagas;

    @NotNull
    @Column(name = "taxa_hora")
    private BigDecimal taxaHora;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricaoPatio() {
        return descricaoPatio;
    }

    public void setDescricaoPatio(String descricaoPatio) {
        this.descricaoPatio = descricaoPatio;
    }

    public Long getNumeroDeVagas() {
        return numeroDeVagas;
    }

    public void setNumeroDeVagas(Long numeroDeVagas) {
        this.numeroDeVagas = numeroDeVagas;
    }

    public BigDecimal getTaxaHora() {
        return taxaHora;
    }

    public void setTaxaHora(BigDecimal taxaHora) {
        this.taxaHora = taxaHora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patio patio = (Patio) o;
        return Objects.equals(codigo, patio.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
