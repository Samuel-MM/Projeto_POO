package br.inatel.cdg.database.brownie;

public abstract class Brownie {
    protected String name;
    protected double price;
    protected String type;
    protected int quantity;

    public Brownie(String nome, double preco, String tipo, int quantity){
        this.name = nome;
        this.price = preco;
        this.type = tipo;
        this.quantity = quantity;
    }

    public abstract double getFinalPriceUnitary();

    public abstract double getFinalPriceTotal();

    public abstract void getInfo();

}
