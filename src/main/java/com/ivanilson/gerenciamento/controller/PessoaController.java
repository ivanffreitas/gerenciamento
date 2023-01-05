package com.ivanilson.gerenciamento.controller;

import com.ivanilson.gerenciamento.dto.PessoaDto;
import com.ivanilson.gerenciamento.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @PostMapping
    public ResponseEntity<Boolean> create(@RequestBody PessoaDto pessoaDto){
        return ResponseEntity.ok().body(service.inserir(pessoaDto));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PessoaDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<PessoaDto>> findByAll() {
        List<PessoaDto> pessoaDtos = service.buscarTodos();
        return ResponseEntity.ok().body(pessoaDtos);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PessoaDto> update(@RequestBody PessoaDto pessoaDto){
        return ResponseEntity.ok().body(service.alterar(pessoaDto));
    }

}
