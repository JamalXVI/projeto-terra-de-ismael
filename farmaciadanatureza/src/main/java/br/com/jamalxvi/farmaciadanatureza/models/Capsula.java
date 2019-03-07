package br.com.jamalxvi.farmaciadanatureza.models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.jamalxvi.farmaciadanatureza.models.interfaces.DuracaoLotavel;
import lombok.Builder;
import lombok.Data;

/**
 * Esta classe é responsável por controlar os tipos de capsulas criadas. Toda cápsula
 * apresenta:<br/>
 * -Entidade Base.<br/>
 * -Lote (Lotável).<br/>
 * -Nome popular e científico.<br/>
 * -Peso mínimo e máximo.<br/>
 * -Duração máxima de um lote.
 * <p>
 * Unidade Padrão da Cápsula: gramas
 * </p>
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "CAPSULA")
@AttributeOverrides(value = {@AttributeOverride(name = "id", column = @Column(name = "ID_CAP")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_CAP")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_CAP")),
    @AttributeOverride(name = "nome", column = @Column(name = "NOM_CAP")),
    @AttributeOverride(name = "nomeCientifico", column = @Column(name = "NOM_CIE_CAP")),})
@Builder
@Data
public class Capsula extends Cientifica implements DuracaoLotavel {
  @Column(name = "PES_MIN_CAP", precision = 4)
  private BigDecimal pesoMinimo;
  @Column(name = "PES_MAX_CAP", precision = 4)
  private BigDecimal pesoMaximo;
  @Column(name = "DUR_LOT_CAP")
  private Long duracaoLote;
  @OneToMany(targetEntity = CapsulaEstoque.class, fetch = FetchType.LAZY, mappedBy = "capsula")
  @JsonManagedReference
  public List<CapsulaEstoque> estoque;
}
