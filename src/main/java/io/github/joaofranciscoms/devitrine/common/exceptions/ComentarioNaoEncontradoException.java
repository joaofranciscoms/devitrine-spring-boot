package io.github.joaofranciscoms.devitrine.common.exceptions;

public class ComentarioNaoEncontradoException extends RuntimeException {
    public ComentarioNaoEncontradoException() {
        super("Comentario não encontrado!");
    }
}
