package br.com.jamalxvi.farmaciadanatureza.exception;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumExcecaoDto;
import lombok.Getter;

/**
 * Por Definir as Mensagens de Exceção
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Getter
public class MensagemExcecao extends RuntimeException {

    private static final long serialVersionUID = 1791564636123821405L;
    private Long idDoRecurso;
    private EnumExcecaoDto codigoEnum;
    private Boolean mostrarId = true;

    public MensagemExcecao(String message, Long idDoRecurso, EnumExcecaoDto codigoEnum) {
        super(message);
        this.idDoRecurso = idDoRecurso;
        this.codigoEnum = codigoEnum;
    }

    public MensagemExcecao(String message, EnumExcecaoDto codigoEnum) {
        super(message);
        this.codigoEnum = codigoEnum;
        this.mostrarId = false;
    }
}
