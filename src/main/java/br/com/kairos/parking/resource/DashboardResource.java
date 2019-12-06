package br.com.kairos.parking.resource;

import br.com.kairos.parking.dto.DashboardEstatisticaDia;
import br.com.kairos.parking.repository.EstacionamentoRepository;
import br.com.kairos.parking.service.DasboardService;
import br.com.kairos.parking.service.EstacionamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardResource {
    

  @Autowired
    private DasboardService dasboardService;


    @GetMapping("/estatisticas/por-dia/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_TICKET') and #oauth2.hasScope('read')")
    public DashboardEstatisticaDia porDia(@PathVariable final Long codigo) {
        return this.dasboardService.porDia(codigo);
    }
    
}
