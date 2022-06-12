package br.inatel.cdg.database.brownie;

public abstract class Brownie {
    protected String name;
    protected double price;
    protected String type;
    protected int quantity;
    protected String database = "data.json";
    protected String[] indexFields = {"ID: ", "Nome: ", "Preço: ", "Tipo: ", "Quantidade: ", "Preço final unidade: " ,
            "Preço final total: "};

    public Brownie(String nome, double preco, String tipo, int quantity){
        this.name = nome;
        this.price = preco;
        this.type = tipo;
        this.quantity = quantity;
    }
    // mostra preco funcão somente para os extends 
    public abstract double getFinalPriceUnitary();

    public abstract double getFinalPriceTotal();
}
