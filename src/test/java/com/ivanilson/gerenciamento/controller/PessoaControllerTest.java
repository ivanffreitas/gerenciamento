package com.ivanilson.gerenciamento.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivanilson.gerenciamento.dto.EnderecoDto;
import com.ivanilson.gerenciamento.dto.PessoaDto;
import com.ivanilson.gerenciamento.enums.TipoEndereco;
import com.ivanilson.gerenciamento.service.PessoaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PessoaService service;

    private final String urlGeral = "/api/v1/pessoas";
    private final ObjectMapper mapper = new ObjectMapper();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate nascimento;

    @Test
    void findByAll() throws Exception {

        EnderecoDto enderecoDto = EnderecoDto.builder()
                .idendereco(1L)
                .logradouro("Rua teste")
                .cep("55000-000")
                .numero("1T")
                .cidade("Cidade Teste")
                .tipoEndereco(TipoEndereco.PRINCIPAL)
                .build();

        List<PessoaDto> pessoa = List.of(PessoaDto.builder()
                .id(1L)
                .nome("Ivanilson")
                .nascimento(nascimento)
                .enderecos(List.of(enderecoDto))
                .build());

        Mockito.when(service.buscarTodos()).thenReturn(pessoa);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get(urlGeral)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void findById() throws Exception {

        EnderecoDto enderecoDto = EnderecoDto.builder()
                .idendereco(1L)
                .logradouro("Rua teste")
                .cep("55000-000")
                .numero("1T")
                .cidade("Cidade Teste")
                .tipoEndereco(TipoEndereco.PRINCIPAL)
                .build();

        PessoaDto pessoa = PessoaDto.builder()
                .id(1L)
                .nome("Ivanilson")
                .nascimento(nascimento)
                .enderecos(List.of(enderecoDto))
                .build();

        Mockito.when(service.buscarPorId(Mockito.any())).thenReturn(pessoa);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get(urlGeral + "/{id}", pessoa.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void create() throws Exception {

      PessoaDto pessoaCreate = PessoaDto.builder()
                .nome("Ivanilson")
                .nascimento(nascimento)
                .enderecos(List.of())
                .build();

        Mockito.when(service.inserir(Mockito.any())).thenReturn(true);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post(urlGeral)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(pessoaCreate))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


}
