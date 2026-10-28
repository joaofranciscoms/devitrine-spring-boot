package io.github.joaofranciscoms.devitrine.common.exceptions;

public class ProjetoNaoEncontradoException extends RuntimeException {
    public ProjetoNaoEncontradoException() {
        super("Projeto não encontrado!");
    }
}
