package br.com.jamalxvi.farmaciadanatureza.repository;

import br.com.jamalxvi.farmaciadanatureza.models.OutrosMedicamentosEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutrosMedicamentosEstoqueRepository extends JpaRepository<OutrosMedicamentosEstoque,
        Long> {
}
