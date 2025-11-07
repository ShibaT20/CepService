package com.cepservice.cep.config;

import com.cepservice.cep.model.Usuario;
import com.cepservice.cep.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Configuration
public class DataLoader {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final Resource usuariosCsv;

    public DataLoader(UsuarioRepository usuarioRepository,
                      PasswordEncoder passwordEncoder,
                      @Value("classpath:db/data/usuarios_teste.csv") Resource usuariosCsv) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuariosCsv = usuariosCsv;
    }

    @PostConstruct
    @Transactional
    public void loadUsuarios() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(usuariosCsv.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            boolean header = true;
            while ((line = reader.readLine()) != null) {
                if (header) { header = false; continue; }
                String[] parts = line.split(";");
                if (parts.length < 2) continue;
                String username = parts[0].trim();
                String rawPassword = parts[1].trim();

                usuarioRepository.findByUsername(username).ifPresentOrElse(u -> {}, () -> {
                    Usuario novo = new Usuario();
                    novo.setUsername(username);
                    novo.setPassword(passwordEncoder.encode(rawPassword));
                    novo.setRole("USER");
                    usuarioRepository.save(novo);
                });
            }
        } catch (Exception e) {
            System.err.println("Falha ao carregar usuarios_teste.csv: " + e.getMessage());
        }
    }
}