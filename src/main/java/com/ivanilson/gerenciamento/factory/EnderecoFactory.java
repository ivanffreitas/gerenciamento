package com.ivanilson.gerenciamento.factory;

import com.ivanilson.gerenciamento.model.Endereco;
import com.ivanilson.gerenciamento.model.dto.EnderecoDto;
import org.springframework.beans.factory.annotation.Autowired;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class EnderecoFactory {

    @Autowired
    private PessoaFactory factory;

    public EnderecoDto toEnderecoDto(Endereco endereco) {
        return 	EnderecoDto.builder()
                .id(endereco.getId())
                .logradouro(endereco.getLogradouro())
                .cep(endereco.getCep())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .tipoEndereco(endereco.getTipoEndereco())
                .pessoa(factory.toPessoaDto(endereco.getPessoa()))
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
                .id(dto.getId())
                .logradouro(dto.getLogradouro())
                .cep(dto.getCep())
                .numero(dto.getNumero())
                .cidade(dto.getCidade())
                .tipoEndereco(dto.getTipoEndereco())
                .pessoa(factory.toPessoa(dto.getPessoa()))
                .build();
    }

    public List<Endereco> toListEnderecos(List<EnderecoDto> dtos){
        return dtos
                .stream()
                .map( d -> toEndereco(d) )
                .collect(Collectors.toList());
    }
    
}
