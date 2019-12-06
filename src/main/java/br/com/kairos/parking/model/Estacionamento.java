package br.com.kairos.parking.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "estacionamento")
public class Estacionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotNull
    @Column(name = "data_entrada")
    private LocalDate dataEntrada;

    @NotNull
    @Column(name = "horario_entrada")
    private LocalTime horarioChegada;

    @Column(name = "horario_saida")
    private LocalTime horarioSaida;

    @Column(name = "valor_periodo")
    private BigDecimal valorPeriodo;

    @Column(name = "tempo_permanencia")
    private BigDecimal tempoPermanencia;

    @NotNull
    @Column(name = "placa_veiculo")
    private String placaVeiculo;

    @NotNull
    @OneToOne
    @JoinColumn(name = "patio")
    private Patio patio;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalTime getHorarioChegada() {
        return horarioChegada;
    }

    public void setHorarioChegada(LocalTime horarioChegada) {
        this.horarioChegada = horarioChegada;
    }

    public LocalTime getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(LocalTime horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    public BigDecimal getValorPeriodo() {
        return valorPeriodo;
    }

    public void setValorPeriodo(BigDecimal valorPeriodo) {
        this.valorPeriodo = valorPeriodo;
    }

    public BigDecimal getTempoPermanencia() {
        return tempoPermanencia;
    }

    public void setTempoPermanencia(BigDecimal tempoPermanencia) {
        this.tempoPermanencia = tempoPermanencia;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public Patio getPatio() {
        return patio;
    }

    public void setPatio(Patio patio) {
        this.patio = patio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estacionamento)) return false;
        Estacionamento that = (Estacionamento) o;
        return Objects.equals(getCodigo(), that.getCodigo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigo());
    }
}
