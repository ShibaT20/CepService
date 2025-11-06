package com.testeoti.cep.repository;

import com.testeoti.cep.model.Cep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CepRepository extends JpaRepository<Cep, Long> {

    @Query(value = "SELECT * FROM cep WHERE cep = :cep", nativeQuery = true)
    Optional<Cep> findByCep(@Param("cep") String cep);

    @Query(value = "SELECT * FROM cep WHERE LOWER(logradouro) LIKE LOWER(CONCAT('%', :logradouro, '%')) ORDER BY id",
            countQuery = "SELECT COUNT(*) FROM cep WHERE LOWER(logradouro) LIKE LOWER(CONCAT('%', :logradouro, '%'))",
            nativeQuery = true)
    Page<Cep> findByLogradouro(@Param("logradouro") String logradouro, Pageable pageable);


    @Query(value = "SELECT * FROM cep WHERE LOWER(cidade) LIKE LOWER(CONCAT('%', :cidade, '%')) ORDER BY id",
            countQuery = "SELECT COUNT(*) FROM cep WHERE LOWER(cidade) LIKE LOWER(CONCAT('%', :cidade, '%'))",
            nativeQuery = true)
    Page<Cep> findByCidade(@Param("cidade") String cidade, Pageable pageable);
}