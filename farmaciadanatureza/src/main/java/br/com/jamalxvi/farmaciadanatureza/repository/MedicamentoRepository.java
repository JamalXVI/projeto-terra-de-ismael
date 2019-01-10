package br.com.jamalxvi.farmaciadanatureza.repository;

import br.com.jamalxvi.farmaciadanatureza.models.interfaces.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    public <T extends Medicamento> List<Medicamento> findItens(Class<T> type);
}
