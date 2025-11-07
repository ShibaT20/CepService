package com.cepservice.cep.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cep")
@Getter @Setter
public class Cep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cep", nullable = false, unique = true, length = 8)
    private String cep;

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Column(name = "uf", nullable = false, length = 2)
    private String uf;

    public Cep() {
    }
}