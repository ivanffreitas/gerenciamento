package com.ivanilson.gerenciamento.service;

import com.ivanilson.gerenciamento.enums.TipoEndereco;
import com.ivanilson.gerenciamento.factory.EnderecoFactory;
import com.ivanilson.gerenciamento.model.Endereco;
import com.ivanilson.gerenciamento.model.dto.EnderecoDto;
import com.ivanilson.gerenciamento.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private EnderecoFactory factory;

    @Autowired
    private PessoaService pessoaService;

    public Boolean inserir(EnderecoDto enderecoDto){
        pessoaService.buscarPorId(enderecoDto.getPessoa().getId());
        Endereco endereco = factory.toEndereco(enderecoDto);

        repository.findByEndereco(endereco.getTipoEndereco(), endereco.getPessoa().getId()).ifPresent(end -> {
            throw new IllegalArgumentException("Já existe um endereco principal");
        });

        repository.save(endereco);

        return true;
    }

    public EnderecoDto buscarPorId(Long id){
        Endereco endereco = repository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("Endereco não encontrada");
        });
        return factory.toEnderecoDto(endereco);
    }

    public List<EnderecoDto> buscarPorIdPessoa(Long id){
        pessoaService.buscarPorId(id);
        List<Endereco> endereco = repository.findByPessoa(id);
        return factory.toListEnderecoDto(endereco);
    }

    public List<EnderecoDto> enderecoPrincipaal(List<EnderecoDto> enderecoDto){

        validarEnderecos(enderecoDto);

        for (EnderecoDto dto: enderecoDto) {
            repository.save(factory.toEndereco(dto));
        }

        return enderecoDto;
    }

    public void validarEnderecos(List<EnderecoDto> enderecoDto){
        int qtd=0;
        for (EnderecoDto dto: enderecoDto) {
            if(dto.getTipoEndereco()== TipoEndereco.PRINCIPAL){
                qtd++;
            }
        }
        if(qtd > 1){
            throw new IllegalArgumentException("Não é possível ter dois endereços principais para a mesma pessoa");
        }
    }

}
