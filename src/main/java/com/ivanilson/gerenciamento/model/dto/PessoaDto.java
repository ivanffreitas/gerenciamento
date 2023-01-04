package com.ivanilson.gerenciamento.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PessoaDto {

    private Long id;
    private String nome;
    private LocalDate nascimento;
    private List<EnderecoDto> enderecos;

}
