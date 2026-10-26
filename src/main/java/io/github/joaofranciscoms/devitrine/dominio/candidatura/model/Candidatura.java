package io.github.joaofranciscoms.devitrine.dominio.candidatura.model;

import io.github.joaofranciscoms.devitrine.dominio.usuario.model.Usuario;
import io.github.joaofranciscoms.devitrine.dominio.vaga.model.Vaga;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "candidatura")
@EntityListeners(AuditingEntityListener.class)
public class Candidatura {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "conteudo", nullable = false, columnDefinition = "TEXT")
    private String mensagem;

    @Column(name = "numero_candidator")
    private int numeroCandidatos;

    @Column(name = "data_postagem")
    @CreatedDate
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "autor_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vaga_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Vaga vaga;
}
