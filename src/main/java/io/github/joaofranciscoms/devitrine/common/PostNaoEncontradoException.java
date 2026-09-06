package io.github.joaofranciscoms.devitrine.common;

public class PostNaoEncontradoException extends RuntimeException {
    public PostNaoEncontradoException() {
        super("Post nao encontrado!");
    }
}
