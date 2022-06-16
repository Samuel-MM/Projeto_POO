package br.inatel.cdg.database.exceptions;

public class ProductAlreadyExists extends Exception{
    public ProductAlreadyExists(String message){
        super(message);
    }
}