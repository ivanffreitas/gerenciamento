package com.ivanilson.gerenciamento.controller;

import com.ivanilson.gerenciamento.dto.EnderecoDto;
import com.ivanilson.gerenciamento.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @PostMapping(value = "/{idpessoa}")
    public ResponseEntity<Boolean> create(@PathVariable Long idpessoa, @RequestBody EnderecoDto enderecoDto){
        return ResponseEntity.ok().body(service.inserir(idpessoa, enderecoDto));
    }

    @GetMapping(value = "/{idpessoa}")
    public ResponseEntity<List<EnderecoDto>> findByIdPessoa(@PathVariable Long idpessoa) {
        return ResponseEntity.ok().body(service.buscarPorIdPessoa(idpessoa));
    }

    @PutMapping(value = "/{idpessoa}")
    public ResponseEntity<List<EnderecoDto>> updateEndereco(@PathVariable Long idpessoa, @RequestBody List<EnderecoDto> enderecos){
        return ResponseEntity.ok().body(service.enderecoPrincipaal(idpessoa, enderecos));
    }
}
