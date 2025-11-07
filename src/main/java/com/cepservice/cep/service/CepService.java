package com.cepservice.cep.service;

import com.cepservice.cep.model.Cep;
import com.cepservice.cep.repository.CepRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CepService {

    private final CepRepository cepRepository;

    public CepService(CepRepository cepRepository) {
        this.cepRepository = cepRepository;
    }

    public Optional<Cep> getByCep(String cep) {
        Optional<Cep> result = Optional.empty();
        result = cepRepository.findByCep(cep);
        return result;
    }

    public Page<Cep> getByLogradouro(String logradouro, Pageable pageable) {
        return cepRepository.findByLogradouro(logradouro, pageable);
    }

    public Page<Cep> getByCidade(String cidade, Pageable pageable) {
        return cepRepository.findByCidade(cidade, pageable);
    }

    public Cep create(Cep cep) {
        return cepRepository.save(cep);
    }

    public Cep update(Long id, Cep cep) {
        Cep atual = cepRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CEP não encontrado"));

        atual.setLogradouro(cep.getLogradouro());
        atual.setBairro(cep.getBairro());
        atual.setCidade(cep.getCidade());

        return cepRepository.save(atual);
    }

}