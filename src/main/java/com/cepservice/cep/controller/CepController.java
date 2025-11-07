package com.cepservice.cep.controller;

import com.cepservice.cep.model.Cep;
import com.cepservice.cep.service.CepService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ceps")
public class CepController {

    private final CepService cepService;

    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<?> getByCep(@PathVariable("cep") String cep) {
        return ResponseEntity.ok(cepService.getByCep(cep));
    }

    @GetMapping("/logradouro")
    public ResponseEntity<?> getByLogradouro(@RequestParam("nome") String logradouro, Pageable pageable) {
        return ResponseEntity.ok(cepService.getByLogradouro(logradouro, pageable));
    }

    @GetMapping("/cidade")
    public ResponseEntity<?> getByCidade(@RequestParam("nome") String cidade, Pageable pageable) {
        return ResponseEntity.ok(cepService.getByCidade(cidade, pageable));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Cep cep) {
        return ResponseEntity.ok(cepService.create(cep));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Cep cep) {
        return ResponseEntity.ok(cepService.update(id, cep));
    }
}
