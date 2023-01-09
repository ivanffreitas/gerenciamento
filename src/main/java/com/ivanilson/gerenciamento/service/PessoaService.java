package com.ivanilson.gerenciamento.service;

import com.ivanilson.gerenciamento.service.exceptions.ObjectnotFoundException;
import com.ivanilson.gerenciamento.factory.PessoaFactory;
import com.ivanilson.gerenciamento.model.Pessoa;
import com.ivanilson.gerenciamento.dto.PessoaDto;
import com.ivanilson.gerenciamento.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    PessoaFactory pessoaFactory;

    @Autowired
    PessoaRepository repository;

    public PessoaDto buscarPorId(Long id){
        Pessoa pessoa = repository.findById(id).orElseThrow(() ->
            new ObjectnotFoundException("Pessoa não encontrada " + id)
        );
        return pessoaFactory.toPessoaDto(pessoa);
    }

    public List<PessoaDto> buscarTodos() {
        List<Pessoa> pessoas = repository.findAll();
        return pessoaFactory.toListPessoaDto(pessoas);
    }

    public Boolean inserir(PessoaDto pessoaDto){
        Pessoa pessoa = pessoaFactory.toPessoa(pessoaDto);
        repository.save(pessoa);
        return true;
    }

    public PessoaDto alterar(PessoaDto pessoaDto){
        buscarPorId(pessoaDto.getId());
        Pessoa pessoa = repository.save(pessoaFactory.toPessoa(pessoaDto));
        return pessoaFactory.toPessoaDto(pessoa);
    }
}
