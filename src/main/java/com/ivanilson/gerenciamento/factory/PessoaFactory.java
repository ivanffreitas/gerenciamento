package com.ivanilson.gerenciamento.factory;

import com.ivanilson.gerenciamento.model.Pessoa;
import com.ivanilson.gerenciamento.model.dto.PessoaDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class PessoaFactory {

    @Autowired
    EnderecoFactory factory;

    public PessoaDto toPessoaDto(Pessoa pessoa) {
        return 	PessoaDto.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .nascimento(pessoa.getNascimento())
                .enderecos(factory.toListEnderecoDto(pessoa.getEnderecos()))
                .build();
    }

    public List<PessoaDto> toPessoaDto(List<Pessoa> pessoas){
        return pessoas
                .stream()
                .map( this::toPessoaDto )
                .collect(Collectors.toList());
    }

    public Pessoa toPessoa(PessoaDto dto) {
        return 	Pessoa.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .nascimento(dto.getNascimento())
                .enderecos(factory.toListEnderecos(dto.getEnderecos()))
                .build();
    }

    public List<Pessoa> toListPessoas(List<PessoaDto> dtos){
        return dtos
                .stream()
                .map( d -> toPessoa(d) )
                .collect(Collectors.toList());
    }

}
