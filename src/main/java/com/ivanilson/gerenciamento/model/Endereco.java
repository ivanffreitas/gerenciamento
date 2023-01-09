package com.ivanilson.gerenciamento.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivanilson.gerenciamento.enums.TipoEndereco;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB002_ENDERECO")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idendereco")
    private Long idendereco;

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "cep", nullable = false, length = 9)
    private String cep;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Column(name = "tipo", nullable = false)
    private TipoEndereco tipoEndereco;

    @ManyToOne
    @JoinColumn(name = "idpessoa", nullable = false)
    private Pessoa pessoa;

}
