package com.ivanilson.gerenciamento.repository;

import com.ivanilson.gerenciamento.enums.TipoEndereco;
import com.ivanilson.gerenciamento.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("SELECT a FROM endereco a WHERE a.tipoEndereco = ?1 AND a.pessoa.id = ?2")
    Optional<Endereco> findByEndereco(TipoEndereco tipoEndereco, Long idPessoa);

    @Query("SELECT a FROM endereco a WHERE a.pessoa.id = ?1")
    List<Endereco> findByPessoa(Long id);
}
