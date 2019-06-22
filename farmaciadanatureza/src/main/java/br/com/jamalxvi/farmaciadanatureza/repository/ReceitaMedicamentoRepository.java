package br.com.jamalxvi.farmaciadanatureza.repository;

import br.com.jamalxvi.farmaciadanatureza.models.ReceitaMedicamento;
import br.com.jamalxvi.farmaciadanatureza.models.ReceitaMedicamentoPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaMedicamentoRepository
    extends JpaRepository<ReceitaMedicamento, ReceitaMedicamentoPk> {
}
