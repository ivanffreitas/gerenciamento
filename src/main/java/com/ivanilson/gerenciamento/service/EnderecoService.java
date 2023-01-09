package com.ivanilson.gerenciamento.service;

import com.ivanilson.gerenciamento.dto.PessoaDto;
import com.ivanilson.gerenciamento.enums.TipoEndereco;
import com.ivanilson.gerenciamento.service.exceptions.DataIntegrityViolationException;
import com.ivanilson.gerenciamento.factory.EnderecoFactory;
import com.ivanilson.gerenciamento.factory.PessoaFactory;
import com.ivanilson.gerenciamento.model.Endereco;
import com.ivanilson.gerenciamento.dto.EnderecoDto;
import com.ivanilson.gerenciamento.model.Pessoa;
import com.ivanilson.gerenciamento.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoFactory factory;

    @Autowired
    private PessoaFactory pessoaFactory;

    @Autowired
    private PessoaService pessoaService;

    public Boolean inserir(Long idpessoa, EnderecoDto enderecoDto){
        PessoaDto pessoaDTO = pessoaService.buscarPorId(idpessoa);
        enderecoDto.setPessoa(pessoaDTO);
        Endereco endereco = factory.toEndereco(enderecoDto);
        endereco.setPessoa(pessoaFactory.toPessoa(pessoaDTO));

        if(endereco.getTipoEndereco() == TipoEndereco.PRINCIPAL){
            enderecoRepository.findByTipoEnderecoAndPessoa(
                    endereco.getTipoEndereco(), endereco.getPessoa()).ifPresent(end -> {
                throw new DataIntegrityViolationException("Já existe um endereco principal");
            });
        }
        enderecoRepository.save(endereco);

        return true;
    }

    public List<EnderecoDto> buscarPorIdPessoa(Long id){
        PessoaDto pessoaDto = pessoaService.buscarPorId(id);
        Pessoa pessoa = pessoaFactory.toPessoa(pessoaDto);
        List<Endereco> endereco = enderecoRepository.findByPessoa(pessoa);
        return factory.toListEnderecoDto(endereco);
    }

    public List<EnderecoDto> enderecoPrincipaal(Long idpessoa, List<EnderecoDto> enderecoDto){

        validarEnderecos(enderecoDto);
        PessoaDto pessoaDTO = pessoaService.buscarPorId(idpessoa);

        for (EnderecoDto dto: enderecoDto) {
            dto.setPessoa(pessoaDTO);
            Endereco endereco = factory.toEndereco(dto);
            endereco.setPessoa(pessoaFactory.toPessoa(pessoaDTO));
            enderecoRepository.save(endereco);
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
            throw new DataIntegrityViolationException("Não é possível ter dois endereços principais para a mesma pessoa");
        }
    }

}
