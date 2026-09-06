package io.github.joaofranciscoms.devitrine.common;

public class ComentarioNaoEncontradoException extends RuntimeException {
    public ComentarioNaoEncontradoException() {
        super("Comentario não encontrado!");
    }
}
