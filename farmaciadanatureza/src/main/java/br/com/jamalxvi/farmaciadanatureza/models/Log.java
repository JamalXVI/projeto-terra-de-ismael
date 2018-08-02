package br.com.jamalxvi.farmaciadanatureza.models;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumLogAcoes;
import lombok.Builder;
import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Classe que representa todos os logs do sistema.
 * Todos os os logos aperesentam:<br/>
 * -Entidade Base.<br/>
 * -Usuário que fez a ação<br/>
 * -Ação feita<br/>
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "LOG")
@AttributeOverrides(value = {
    @AttributeOverride(name = "id", column = @Column(name = "ID_LOG")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_LOG")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_LOG")),
})
@Builder
@Data
public class Log extends EntidadeBase {
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Usuario.class)
  @JoinColumn(name = "ID_USR")
  private Usuario usuario;
  @Enumerated(value = EnumType.ORDINAL)
  @Column(name = "ACT_LOG")
  private EnumLogAcoes acao;
  @Column(name = "ID_ACT_LOG")
  @NotNull
  private Long idAcao;
  @Column(name = "DES_ACT_LOG")
  @Size(max = 255)
  private String descricaoAcao;
}
