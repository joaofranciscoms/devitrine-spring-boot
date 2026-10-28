package io.github.joaofranciscoms.devitrine.dominio.tecnologia.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "tecnologia")
public class Tecnologia {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", unique = true, nullable = false)
    private String nome;
}
