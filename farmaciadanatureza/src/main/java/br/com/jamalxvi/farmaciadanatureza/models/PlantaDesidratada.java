package br.com.jamalxvi.farmaciadanatureza.models;

import br.com.jamalxvi.farmaciadanatureza.models.interfaces.DuracaoLotavel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
 * Esta classe é responsável por controlar os tipos de plantas desidratadas criadas.
 * Toda cápsula apresenta:<br/>
 * -Entidade Base.<br/>
 * -Lote (Lotável).<br/>
 * -Nome popular e científico.<br/>
 * -Duração máxima de um lote.
 * <p>Unidade Padrão da Cápsula: Sacos</p>
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "PLANTA_DESIDRATADA")
@AttributeOverrides(value = {
    @AttributeOverride(name = "id", column = @Column(name = "ID_PLT_DES")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_PLT_DES")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_PLT_DES")),
    @AttributeOverride(name = "nomePopular", column = @Column(name = "NOM_PLT_DES")),
    @AttributeOverride(name = "nomeCientifico", column = @Column(name = "NOM_CIE_PLT_DES")),
})
@Builder
@Data
public class PlantaDesidratada extends Cientifica implements DuracaoLotavel {
  @Column(name = "DUR_LOT_PLT_DES")
  private Long duracaoLote;
  @OneToMany(mappedBy = "plantaDesidratada", fetch = FetchType.LAZY,
      targetEntity = PlantaDesidratadaEstoque.class)
  @JsonManagedReference
  private List<PlantaDesidratadaEstoque> estoque;
}
