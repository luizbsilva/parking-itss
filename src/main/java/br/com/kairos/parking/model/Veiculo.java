package br.com.kairos.parking.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotNull
    @Column(name = "placa")
    private String placaVeiculo;

    @NotNull
    @Column(name = "cor")
    private String corVeiculo;

    @NotNull
    @Column(name = "modelo")
    private String modeloVeiculo;

    @NotNull
    @OneToOne
    @JoinColumn(name = "marca")
    private MarcaVeiculo marcaVeiculo;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public String getCorVeiculo() {
        return corVeiculo;
    }

    public void setCorVeiculo(String corVeiculo) {
        this.corVeiculo = corVeiculo;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public void setModeloVeiculo(String modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    public MarcaVeiculo getMarcaVeiculo() {
        return marcaVeiculo;
    }

    public void setMarcaVeiculo(MarcaVeiculo marcaVeiculo) {
        this.marcaVeiculo = marcaVeiculo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return Objects.equals(codigo, veiculo.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
