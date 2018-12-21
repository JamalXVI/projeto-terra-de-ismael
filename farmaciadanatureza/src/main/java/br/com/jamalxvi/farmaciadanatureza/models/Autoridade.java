package br.com.jamalxvi.farmaciadanatureza.models;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumAutorizacaoUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Classe que representa a autoridade, considerando as permissões de usuário
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name="AUTORIDADE")
@Data
@Builder
public class Autoridade implements GrantedAuthority, Serializable {


    @Id
    @Column(name="ID_AUT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter(onMethod = @_(@JsonIgnore))
    Long id;

    @Enumerated( EnumType.STRING)
    @Column(name="AUT_AUT", columnDefinition = "enum('ROLE_USUARIO','ROLE_ADMIN')")
    @Getter(onMethod = @_(@JsonIgnore))
    EnumAutorizacaoUsuario autorizacao;

    @Override
    public String getAuthority() {
        return autorizacao.name();
    }

}
