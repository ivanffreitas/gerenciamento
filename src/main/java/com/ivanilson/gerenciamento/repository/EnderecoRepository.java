package com.ivanilson.gerenciamento.repository;

import com.ivanilson.gerenciamento.enums.TipoEndereco;
import com.ivanilson.gerenciamento.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Optional<Endereco> findByTipoEnderecoAndPessoa(TipoEndereco tipo, Long idpessoa);

    List<Endereco> findByPessoa(Long idpessoa);
}
