package br.com.jamalxvi.farmaciadanatureza.models;

import br.com.jamalxvi.farmaciadanatureza.models.interfaces.DuracaoLotavel;
import br.com.jamalxvi.farmaciadanatureza.models.interfaces.TipoEstoque;
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
 * Esta classe é responsável por controlar os tipos de pomadas criadas.
 * Toda pomada apresenta:<br/>
 * -Entidade Base.<br/>
 * -Lote (Lotável).<br/>
 * -Nome popular e científico.<br/>
 * -Duração máxima de um lote.
 * <p>Unidade Padrão da Pomadas: gramas/unidades (2 classes diferentes)</p>
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "POMADA")
@AttributeOverrides(value = {
        @AttributeOverride(name = "id", column = @Column(name = "ID_POM")),
        @AttributeOverride(name = "versao", column = @Column(name = "VER_POM")),
        @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_POM")),
        @AttributeOverride(name = "nome", column = @Column(name = "NOM_POM")),
        @AttributeOverride(name = "nomeCientifico", column = @Column(name = "NOM_CIE_POM")),
})
@Builder
@Data
public class Pomada extends Cientifica implements DuracaoLotavel, TipoEstoque {
    @Column(name = "DUR_LOT_POM")
    private Long duracaoLote;
    @OneToMany(mappedBy = "pomada", fetch = FetchType.LAZY,
            targetEntity = PomadaEstoque.class)
    @JsonManagedReference
    private List<PomadaEstoque> estoque;
}
