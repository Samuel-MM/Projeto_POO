package br.inatel.cdg.database.read;


import br.inatel.cdg.database.interfaces.BrownieInfo;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import br.inatel.cdg.database.brownie.Brownie;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Read extends Brownie implements BrownieInfo {

    public Read(String nome, double preco, String tipo, int quantity) {
        super(nome, preco, tipo, quantity);
        //TODO Auto-generated constructor stub
    }
    private String database = "../database/data.json";

    public void ReadBrownie() throws ParseException {

        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(database)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            System.out.println(jsonObject);

            String name = (String) jsonObject.get("name");
            System.out.println(name);

            double price = (double) jsonObject.get("price");
            System.out.println(price);

            String type = (String) jsonObject.get("type");
            System.out.println(type);
            
            int quantity = (int) jsonObject.get("quantity");
            System.out.println(quantity);
            
        } catch (IOException error) {
                error.printStackTrace();
        }
    }


    @Override
    public double getFinalPriceUnitary() {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public double getFinalPriceTotal() {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public void getInfo() {
        // TODO Auto-generated method stub
        
    }
}
