package io.github.joaofranciscoms.devitrine.dominio.projeto.model;

import io.github.joaofranciscoms.devitrine.dominio.tecnologia.model.Tecnologia;
import io.github.joaofranciscoms.devitrine.dominio.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "projeto")
@EntityListeners(AuditingEntityListener.class)
public class Projeto {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descricao", nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoProjeto tipo;

    @Column(name = "link")
    private String link;

    @Column(name = "data_postagem")
    @CreatedDate
    private LocalDateTime dataPostagem;

    @Column(name = "data_atualizacao")
    @LastModifiedDate
    private LocalDateTime dataAtualizacao;

    @ManyToMany
    @JoinTable(
            name = "projeto_tecnologia",
            joinColumns = @JoinColumn(name = "projeto_id"),
            inverseJoinColumns = @JoinColumn(name = "tecnologia_id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Tecnologia> tecnologias = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "autor_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Usuario autor;
}
