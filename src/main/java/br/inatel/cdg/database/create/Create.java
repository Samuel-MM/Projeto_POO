package br.inatel.cdg.database.create;

import br.inatel.cdg.database.brownie.Brownie;

import java.io.*;
import java.util.Objects;

import br.inatel.cdg.database.exceptions.ProductAlreadyExists;
import br.inatel.cdg.database.interfaces.ManipulateData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Create extends Brownie implements ManipulateData {

    public Create(String name, double price, String type, int quantity) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
        selectItem(name);
    }

    public Create() {
        fileExistPutItem();
    }

    @Override
    public void getInfo() {
        System.out.println("O seu brownie será criado com os seguintes dados: " + name + " - " + type + " - R$" +
                String.format("%.2f", price) + " - " + quantity);
    }

    private void putItem() {
        JSONObject brownieInfo = new JSONObject();

        insertItemsJson(brownieInfo);

        JSONArray brownieArray = new JSONArray();
        brownieArray.add(brownieInfo);

        writeFile(brownieArray);
    }

    private void fileExistPutItem() {
        JSONParser jsonParser = new JSONParser();
        JSONObject brownieInfo = new JSONObject();
        try {
            Object obj = jsonParser.parse(new FileReader(database));
            JSONArray brownieArray = (JSONArray) obj;

            insertItemsJson(brownieInfo);

            brownieArray.add(brownieInfo);
            writeFile(brownieArray);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(JSONArray brownieArray){

        try (FileWriter file = new FileWriter(database)) {
            file.write(brownieArray.toJSONString());
            file.flush();
            if(name != null) getInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void selectItem(String brownieName){

        File f = new File(database);

        if (!f.exists()) {
            putItem();
        }
        else {
            JSONParser jsonParser = new JSONParser();

            try (FileReader reader = new FileReader(database)) {

                Object obj = jsonParser.parse(reader);

                JSONArray brownieList = (JSONArray) obj;

                try {
                    for (Object brownie : brownieList) {
                        brownieExists = findItem((JSONObject) brownie, brownieName);
                        if (brownieExists) break;
                    }
                    if (!brownieExists) {
                        fileExistPutItem();
                    } else {
                        throw new ProductAlreadyExists("Este produto já existe!");
                    }
                } catch (ProductAlreadyExists e) {
                    System.out.println(e.getMessage());
                }

            } catch (ParseException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean findItem(JSONObject brownie, String brownieName) {
        return Objects.equals(brownie.get("Nome"), brownieName);
    }

    private void insertItemsJson(JSONObject brownieInfo){
        String[] brownieData = {
                name,
                String.valueOf(price).replace(".", ","),
                type,
                String.valueOf(quantity),
                String.format("%.2f", getFinalPriceUnitary()),
                String.format("%.2f", getFinalPriceTotal())
        };
        for (int i = 0; i < indexFields.length; i++) {
            brownieInfo.put(indexFields[i], brownieData[i]);
        }
    }

    @Override
    public double getFinalPriceUnitary() {
        return price * 0.1 + price;
    }

    // polimorfismo
    @Override
    public double getFinalPriceTotal() {
        return getFinalPriceUnitary() * quantity;
    }
}
