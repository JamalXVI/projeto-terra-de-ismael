package br.com.jamalxvi.farmaciadanatureza.models.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Define o Dto de informações do Médico (dados não sensíveis) para ser exibida para o
 * usuário do sistema
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Data
@Builder
public class MedicoDto {
    private Long codigo;
    private String crm;
    private String nome;
    private String sobrenome;
    private String cpf;
    private Boolean ativo;
}
