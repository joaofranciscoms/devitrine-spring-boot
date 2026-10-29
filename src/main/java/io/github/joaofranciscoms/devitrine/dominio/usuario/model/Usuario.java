package io.github.joaofranciscoms.devitrine.dominio.usuario.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "usuario")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipo;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name= "senha", nullable = false)
    private String senha;
}
