package br.inatel.cdg.database.update;

import br.inatel.cdg.database.brownie.Brownie;
import br.inatel.cdg.database.create.Create;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Update extends Brownie {

    boolean brownieExists = false;
    Scanner entradaUpdate = new Scanner(System.in);

    public void selectItem(String brownieName){

        JSONParser jsonParser = new JSONParser();


        try (FileReader reader = new FileReader(database)) {

            Object obj = jsonParser.parse(reader);

            JSONArray brownieList = (JSONArray) obj;

            for (Object brownie : brownieList) {
                brownieExists = findItem((JSONObject) brownie, brownieName);
                if (brownieExists) {
                    System.out.println("Entre com o parâmetro a ser editado");
                    updateBrownieInfo((JSONObject) brownie, entradaUpdate.nextLine());
                    break;
                }
            }
            Create create = new Create();
            create.writeFile(brownieList);


        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    private boolean findItem(JSONObject brownie, String brownieName) {
        return Objects.equals(brownie.get("Nome"), brownieName);
    }

    private void updateBrownieInfo(JSONObject brownie, String parameter) {
        System.out.println("Entre com o novo valor");
        if(Objects.equals(parameter, "Preço final total")) {
            brownie.put(parameter, entradaUpdate.nextLine().replace(".", ","));
        }
        else {
            brownie.put(parameter, entradaUpdate.nextLine());
        }
    }


    @Override
    public double getFinalPriceUnitary() {
        return 0;
    }

    @Override
    public double getFinalPriceTotal() {
        return 0;
    }

    public void getInfo(){
        System.out.println("Você tem certeza que deseja excluir o seguinte produto: " + "-" + "?");
    }
}
