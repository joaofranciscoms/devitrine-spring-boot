package io.github.joaofranciscoms.devitrine.dominio.colaboracao.model;

import io.github.joaofranciscoms.devitrine.dominio.ideia.model.Ideia;
import io.github.joaofranciscoms.devitrine.dominio.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "colaboracao")
@EntityListeners(AuditingEntityListener.class)
public class Colaboracao {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "conteudo", nullable = false, columnDefinition = "TEXT")
    private String conteudo;

    @Column(name = "data_postagem")
    @CreatedDate
    private LocalDateTime dataPostagem;

    @Column(name = "data_atualizacao")
    @LastModifiedDate
    private LocalDateTime dataAtualizacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ideia_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Ideia ideia;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "autor_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Usuario autor;
}
