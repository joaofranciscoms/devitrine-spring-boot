package io.github.joaofranciscoms.devitrine.common.exceptions;

public class VagaNaoEncontradaException extends RuntimeException {
    public VagaNaoEncontradaException() {
        super("Vaga não encontrada!");
    }
}
