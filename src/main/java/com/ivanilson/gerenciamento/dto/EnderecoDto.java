package com.ivanilson.gerenciamento.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivanilson.gerenciamento.enums.TipoEndereco;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDto {

    private Long idendereco;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private TipoEndereco tipoEndereco;

    @JsonIgnore
    private PessoaDto pessoa;

}
