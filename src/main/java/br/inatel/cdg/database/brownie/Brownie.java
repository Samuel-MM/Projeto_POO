package br.inatel.cdg.database.brownie;

import java.util.Scanner;

public abstract class Brownie {
    protected String name;
    protected double price;
    protected String type;
    protected int quantity;
    protected String database = "data.json";
    protected String[] indexFields = {"Nome", "Preço", "Tipo", "Quantidade", "Preço final unidade" ,
            "Preço final total"};
    protected boolean brownieExists = false;
    protected Scanner entradaUpdate = new Scanner(System.in);

    // mostra preco funcão somente para os extends 
    public abstract double getFinalPriceUnitary();

    public abstract double getFinalPriceTotal();

    public abstract void getInfo();
}
