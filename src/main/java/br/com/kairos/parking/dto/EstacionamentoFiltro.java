package br.com.kairos.parking.dto;

import br.com.kairos.parking.model.Patio;

import java.time.LocalDate;

public class EstacionamentoFiltro {
    private Patio patio ;
    private LocalDate dataRelatorio;

    public Patio getPatio() {
        return patio;
    }

    public void setPatio(Patio patio) {
        this.patio = patio;
    }

    public LocalDate getDataRelatorio() {
        return dataRelatorio;
    }

    public void setDataRelatorio(LocalDate dataRelatorio) {
        this.dataRelatorio = dataRelatorio;
    }
}
