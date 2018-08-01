package br.com.jamalxvi.farmaciadanatureza.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Esta classe é a controlodadora de florais. <b>Não existe um controle de saída de florais</b>,
 * apenas um consta a existência deste no sistema.
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "FLORAL")
@AttributeOverrides(value = {
    @AttributeOverride(name = "id", column = @Column(name = "ID_FLO")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_FLO")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_FLO")),
})
@Builder
@Data
public class Floral extends EntidadeBase {
  @Column(name = "NOM_FLO")
  private String nome;
}
