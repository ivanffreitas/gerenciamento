package com.ivanilson.gerenciamento.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB001_PESSOA")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpessoa")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "nascimento", nullable = false)
    private LocalDate nascimento;

    @OneToMany(mappedBy = "pessoa")
    private List<Endereco> enderecos;

}
