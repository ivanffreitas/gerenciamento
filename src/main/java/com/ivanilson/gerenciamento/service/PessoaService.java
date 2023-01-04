package com.ivanilson.gerenciamento.service;

import com.ivanilson.gerenciamento.factory.PessoaFactory;
import com.ivanilson.gerenciamento.model.Pessoa;
import com.ivanilson.gerenciamento.model.dto.PessoaDto;
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
        Pessoa pessoa = repository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("Pessoa não encontrada");
        });
        return pessoaFactory.toPessoaDto(pessoa);
    }

    public List<PessoaDto> findAll() {
        List<Pessoa> pessoas = repository.findAll();
        return pessoaFactory.toListPessoaDto(pessoas);
    }

    public Boolean inserir(PessoaDto pessoaDto){
        Pessoa pessoa = pessoaFactory.toPessoa(pessoaDto);
        repository.save(pessoa);
        return true;
    }

    public Boolean alterar(PessoaDto pessoaDto){
        buscarPorId(pessoaDto.getId());
        repository.save(pessoaFactory.toPessoa(pessoaDto));
        return true;
    }
}
