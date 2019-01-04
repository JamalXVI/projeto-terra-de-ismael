package br.com.jamalxvi.farmaciadanatureza.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicoDto {
    private String crm;
    private String nome;
    private String sobrenome;
    private String cpf;
    private Boolean ativo;
}
