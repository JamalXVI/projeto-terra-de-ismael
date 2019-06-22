package br.com.jamalxvi.farmaciadanatureza.repository;

import br.com.jamalxvi.farmaciadanatureza.models.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
}
