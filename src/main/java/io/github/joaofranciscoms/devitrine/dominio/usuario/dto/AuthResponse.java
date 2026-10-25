package io.github.joaofranciscoms.devitrine.dominio.usuario.dto;

public record AuthResponse(String token, String tipo, long tempoParaExpirar, String nome) {
}
