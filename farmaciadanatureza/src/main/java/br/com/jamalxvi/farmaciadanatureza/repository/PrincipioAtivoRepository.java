package br.com.jamalxvi.farmaciadanatureza.repository;

import br.com.jamalxvi.farmaciadanatureza.models.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jamalxvi.farmaciadanatureza.models.PrincipioAtivo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrincipioAtivoRepository extends JpaRepository<PrincipioAtivo, Long> {
  @Query(value = "SELECT p.* FROM PRINCIPIO_ATIVO p where p.NOM_PRI_ATV like %:pesquisa% or "
      + "p.NOM_CIE_PRI_ATV like %:pesquisa% LIMIT :limite", nativeQuery = true)
  List<PrincipioAtivo> findByPesquisa(@Param("pesquisa") String pesquisa,
      @Param("limite") Integer limite);
}
