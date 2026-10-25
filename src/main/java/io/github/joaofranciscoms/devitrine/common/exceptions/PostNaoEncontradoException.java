package io.github.joaofranciscoms.devitrine.common.exceptions;

public class PostNaoEncontradoException extends RuntimeException {
    public PostNaoEncontradoException() {
        super("Post nao encontrado!");
    }
}
