package br.inatel.cdg.database.create;

import br.inatel.cdg.database.brownie.Brownie;

import java.io.*;

import br.inatel.cdg.database.interfaces.ManipulateData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Create extends Brownie implements ManipulateData {

    public Create(String name, double price, String type, int quantity){
        super(name, price, type, quantity);
        saveItem();
    }

    @Override
    public double getFinalPriceUnitary(){
        return price * 0.1 + price;
    }

    public void saveItem(){

        File f = new File(database);
        if(!f.exists())
            putItem();
        else
            fileExistPutItem();
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

    @Override
    public void putItem(){
        JSONObject brownieInfo = new JSONObject();
        String[] brownieData = {
                "1",
                name,
                String.valueOf(price),
                type,
                String.valueOf(quantity),
                String.format("%.2f", getFinalPriceUnitary()),
                String.format("%.2f", getFinalPriceTotal())
        };
        for(int i = 0; i <= 6; i++){
            brownieInfo.put(indexFields[i], brownieData[i]);
        }
        JSONArray userList = new JSONArray();
        userList.add(brownieInfo);

        try (FileWriter file = new FileWriter(database)) {
            file.write(userList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void fileExistPutItem(){
        JSONParser jsonParser = new JSONParser();
        JSONObject brownieInfo = new JSONObject();
        try {
            Object obj = jsonParser.parse(new FileReader(database));
            JSONArray jsonArray = (JSONArray)obj;

            // System.out.println(jsonArray);

            String[] brownieData = {
                    "1",
                    name,
                    String.valueOf(price),
                    type,
                    String.valueOf(quantity),
                    String.format("%.2f", getFinalPriceUnitary()),
                    String.format("%.2f", getFinalPriceTotal())
            };
            for(int i = 0; i <= 6; i++){
                brownieInfo.put(indexFields[i], brownieData[i]);
            }

            jsonArray.add(brownieInfo);
            //System.out.println(jsonArray);

            try (FileWriter file = new FileWriter(database)) {
                file.write(jsonArray.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

}
