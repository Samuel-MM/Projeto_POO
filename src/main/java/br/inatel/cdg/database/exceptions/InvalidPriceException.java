package br.inatel.cdg.database.exceptions;
// class para exceptions/erros 
public class InvalidPriceException extends Exception{
    public InvalidPriceException(String message){
        super(message);
    }
}
