package com.ivanilson.gerenciamento.model;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB002_ENDERECO")
public class Endereco {

    @Id
    @GeneratedValue
    @Column(name = "idendereco")
    private Long id;

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @ManyToOne
    @JoinColumn(name = "idpessoa", nullable = false)
    private Pessoa pessoa;

}
