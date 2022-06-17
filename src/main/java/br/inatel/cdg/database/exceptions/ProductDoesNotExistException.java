package br.inatel.cdg.database.exceptions;
// class para exceptions/erros
public class ProductDoesNotExistException extends Exception{
    public ProductDoesNotExistException(String message){
        super(message);
    }
}
