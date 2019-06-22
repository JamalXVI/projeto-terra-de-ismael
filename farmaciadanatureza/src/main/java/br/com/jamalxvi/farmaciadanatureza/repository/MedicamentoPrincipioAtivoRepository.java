package br.com.jamalxvi.farmaciadanatureza.repository;

import br.com.jamalxvi.farmaciadanatureza.models.MedicamentoPrincipioAtivo;
import br.com.jamalxvi.farmaciadanatureza.models.MedicamentoPrincipioAtivoPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoPrincipioAtivoRepository
    extends JpaRepository<MedicamentoPrincipioAtivo, MedicamentoPrincipioAtivoPk> {
}
