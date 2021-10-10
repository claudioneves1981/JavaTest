package br.com.sigabem.service.exception;

public class CepException extends Exception{

    public CepException(String cep) {
        super("Frete invalido para o CEP "+ cep);
    }
}
