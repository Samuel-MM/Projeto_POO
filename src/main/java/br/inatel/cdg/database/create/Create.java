package br.inatel.cdg.database.create;

import br.inatel.cdg.database.brownie.Brownie;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import br.inatel.cdg.database.interfaces.BrownieInfo;
import org.json.simple.JSONObject;
import org.json.JSONException;

public class Create extends Brownie implements BrownieInfo {

    public Create(String name, double price, String type, int quantity){
        super(name, price, type, quantity);
        salvarBrownie();
    }

    @Override
    public double getFinalPriceUnitary(){
        return price * 0.1 + price;
    }

    public void salvarBrownie() {
        JSONObject json = new JSONObject();
        Path file = Paths.get(database);
        try {
            String[] brownieData = {
                    String.valueOf(Files.lines(file).count() + 1),
                    name,
                    String.valueOf(price),
                    type,
                    String.valueOf(quantity),
                    String.format("%.2f", getFinalPriceUnitary()),
                    String.format("%.2f", getFinalPriceTotal())
            };
            for(int i = 0; i <= 6; i++){
                json.put(indexFields[i], brownieData[i]);
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        try {
            if(Files.lines(file).findAny().isEmpty()){
                Files.writeString(file, json.toString());
            }
            //If there's content append the new line to te end of the file
            else {
                String newLine = System.getProperty("line.separator") + json;
                Files.write(file, newLine.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // polimorfismo
    @Override
    public double getFinalPriceTotal(){
        return getFinalPriceUnitary() * quantity;
    }

    @Override
    public void getInfo(){
        System.out.println("O seu brownie será criado com os seguintes dados: " + name + " - " + type + " - R$" +
                String.format("%.2f", price) + " - " + quantity);
    }

}
