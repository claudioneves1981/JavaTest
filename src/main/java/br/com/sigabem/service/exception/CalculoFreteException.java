package br.com.sigabem.service.exception;

public class CalculoFreteException extends Exception{

    public CalculoFreteException(Long id) {
        super("Frete invalido para o ID "+ id);
    }
}
