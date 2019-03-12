package br.com.jamalxvi.farmaciadanatureza.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jamalxvi.farmaciadanatureza.models.Pessoa;
import org.springframework.data.repository.query.Param;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

  Optional<Pessoa> findByCpf(String cpf);

  @Query(
      value = "SELECT p.* FROM pessoa p where p.nom_pes LIKE %:pesquisa% or p.sob_pes LIKE %:pesquisa% or p.cpf_pes LIKE %:pesquisa% LIMIT :limite",
      nativeQuery = true)
  List<Pessoa> findByPesquisa(@Param("pesquisa") String pesquisa, @Param("limite") Integer limite);
}
