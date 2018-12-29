package br.com.jamalxvi.farmaciadanatureza.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Esta classe é responsável registrar as pomadas que não possuem estoques.
 * -Entidade Base.<br/>
 * -Nome;<br/>
 * -Quantidade;<br/>
 * <p>Unidade Padrão de pomadas: unidades.</p>
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "POMADA_SEM_CONTROLE")
@AttributeOverrides(value = {
        @AttributeOverride(name = "id", column = @Column(name = "ID_POM_SEM_CTL")),
        @AttributeOverride(name = "versao", column = @Column(name = "VER_POM_SEM_CTL")),
        @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_POM_SEM_CTL"))
})
@Builder
@Data
public class PomadaSemControle extends EntidadeBase{
    @Column(name = "NOM_POM_SEM_CTL")
    private String nome;
    @Column(name = "QTD_POM_SEM_CTL")
    private BigDecimal quantidae;
}
