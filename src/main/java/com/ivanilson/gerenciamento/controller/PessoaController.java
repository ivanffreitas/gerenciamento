package com.ivanilson.gerenciamento.controller;

import com.ivanilson.gerenciamento.model.dto.PessoaDto;
import com.ivanilson.gerenciamento.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @PostMapping
    public ResponseEntity<Boolean> inserir(@RequestBody PessoaDto pessoaDto){
        return ResponseEntity.ok().body(service.inserir(pessoaDto));
    }

}
