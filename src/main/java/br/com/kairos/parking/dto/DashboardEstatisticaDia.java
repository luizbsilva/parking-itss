package br.com.kairos.parking.dto;

import java.time.LocalDate;

public class DashboardEstatisticaDia {

    private LocalDate dia;
    private Long vagas;
    private Long vagasOcupadas;
    private Long vagasLivres;

    public DashboardEstatisticaDia(LocalDate dia, Long vagas, Long vagasOcupadas, Long vagasLivres) {
        this.dia = dia;
        this.vagas = vagas;
        this.vagasOcupadas = vagasOcupadas;
        this.vagasLivres = vagasLivres;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public Long getVagas() {
        return vagas;
    }

    public void setVagas(Long vagas) {
        this.vagas = vagas;
    }

    public Long getVagasOcupadas() {
        return vagasOcupadas;
    }

    public void setVagasOcupadas(Long vagasOcupadas) {
        this.vagasOcupadas = vagasOcupadas;
    }

    public Long getVagasLivres() {
        return vagasLivres;
    }

    public void setVagasLivres(Long vagasLivres) {
        this.vagasLivres = vagasLivres;
    }
}
