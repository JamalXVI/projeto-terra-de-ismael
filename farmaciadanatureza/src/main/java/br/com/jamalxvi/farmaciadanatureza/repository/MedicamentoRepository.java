package br.com.jamalxvi.farmaciadanatureza.repository;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumTipoMedicamento;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jamalxvi.farmaciadanatureza.models.Medicamento;

import java.util.List;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    List<Medicamento> findByTipoMedicamento(EnumTipoMedicamento tipoMedicamento);
}
