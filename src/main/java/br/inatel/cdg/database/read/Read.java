package br.inatel.cdg.database.read;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import br.inatel.cdg.database.brownie.Brownie;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Read extends Brownie {

    public void ReadBrownie(){

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(database)) {

            Object obj = jsonParser.parse(reader);

            JSONArray brownieList = (JSONArray) obj;
            System.out.println(brownieList.size());
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

    }
}