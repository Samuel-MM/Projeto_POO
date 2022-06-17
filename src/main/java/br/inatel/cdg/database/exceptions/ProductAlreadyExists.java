package br.inatel.cdg.database.exceptions;
// class para exceptions/erros
public class ProductAlreadyExists extends Exception{
    public ProductAlreadyExists(String message){
        super(message);
    }
}