package br.com.jamalxvi.farmaciadanatureza.repository;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumAutorizacaoUsuario;
import br.com.jamalxvi.farmaciadanatureza.models.Autoridade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoridadeRepository extends JpaRepository<Autoridade, Long> {
  Autoridade findByAutorizacao(EnumAutorizacaoUsuario autorizacaoUsuario);
}
