package com.ivanilson.gerenciamento.factory;

import com.ivanilson.gerenciamento.model.Endereco;
import com.ivanilson.gerenciamento.dto.EnderecoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnderecoFactory {

    @Autowired
    private PessoaFactory factory;

     public EnderecoDto toEnderecoDto(Endereco endereco) {
        return 	EnderecoDto.builder()
                .idendereco(endereco.getIdendereco())
                .logradouro(endereco.getLogradouro())
                .cep(endereco.getCep())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .tipoEndereco(endereco.getTipoEndereco())
                .build();
    }

    public List<EnderecoDto> toListEnderecoDto(List<Endereco> enderecos){
        return enderecos
                .stream()
                .map( this::toEnderecoDto )
                .collect(Collectors.toList());
    }

    public Endereco toEndereco(EnderecoDto dto) {
        return 	Endereco.builder()
                .idendereco(dto.getIdendereco())
                .logradouro(dto.getLogradouro())
                .cep(dto.getCep())
                .numero(dto.getNumero())
                .cidade(dto.getCidade())
                .tipoEndereco(dto.getTipoEndereco())
                .build();
    }

    public List<Endereco> toListEnderecos(List<EnderecoDto> dtos){
        return dtos
                .stream()
                .map( d -> toEndereco(d) )
                .collect(Collectors.toList());
    }
    
}
