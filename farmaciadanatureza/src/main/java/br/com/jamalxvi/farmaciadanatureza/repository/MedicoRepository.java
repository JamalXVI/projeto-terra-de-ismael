package br.com.jamalxvi.farmaciadanatureza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.jamalxvi.farmaciadanatureza.models.Medicamento;
import br.com.jamalxvi.farmaciadanatureza.models.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
  @Query(value = "SELECT m.*, p.* FROM medico m INNER JOIN pessoa p on m.id_pes = p.id_pes where"
      + " p.nom_pes LIKE %:pesquisa% or p.sob_pes LIKE %:pesquisa% or p.cpf_pes LIKE"
      + " %:pesquisa% LIMIT :limite", nativeQuery = true)
  List<Medico> findByPesquisa(@Param("pesquisa") String pesquisa,
      @Param("limite") Integer limite);
}
