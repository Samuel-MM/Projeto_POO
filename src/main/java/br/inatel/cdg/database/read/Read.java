package br.inatel.cdg.database.read;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import br.inatel.cdg.database.brownie.Brownie;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Read extends Brownie {

    public void readBrownie(){

        JSONParser jsonParser = new JSONParser();
        // trycatch para evitar quebra no codigo caso haja erros
        try (FileReader reader = new FileReader(database)) {

            Object obj = jsonParser.parse(reader);

            JSONArray brownieList = (JSONArray) obj;

            getInfo();

            brownieList.forEach(brownie -> showBrownieInfo((JSONObject) brownie));

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showBrownieInfo(JSONObject brownie) {

        for(String item : indexFields){
            String info = (String) brownie.get(item);
            if(Objects.equals(item, "Preço final total")){
                System.out.print(item + ": " + info );
                break;
            }
            System.out.print(item + ": " + info + " - ");
        }
        System.out.println();
    }
    // polimorfismo override
    @Override
    public double getFinalPriceUnitary() {
        return 0;
    }

    @Override
    public double getFinalPriceTotal() {
        return 0;
    }

    @Override
    public void getInfo() {
        System.out.println("Aqui está sua lista com todos os produtos: ");
    }
}