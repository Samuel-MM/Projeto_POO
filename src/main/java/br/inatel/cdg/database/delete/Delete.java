package br.inatel.cdg.database.delete;

import br.inatel.cdg.database.brownie.Brownie;
import br.inatel.cdg.database.create.Create;
import br.inatel.cdg.database.exceptions.ProductDoesNotExistException;
import br.inatel.cdg.database.interfaces.ManipulateData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class Delete extends Brownie implements ManipulateData {
    // polimorfismo override
    @Override
    public void selectItem(String brownieName){

        JSONParser jsonParser = new JSONParser();
        // trycatch para evitar quebra no codigo caso aja erros
        try (FileReader reader = new FileReader(database)) {

            Object obj = jsonParser.parse(reader);

            JSONArray brownieList = (JSONArray) obj;
            try {
                for (int i = 0; i < brownieList.size(); i++) {
                    brownieExists = findItem((JSONObject) brownieList.get(i), brownieName);
                    if (brownieExists) {
                        brownieList.remove(i);
                        break;
                    } else if(i == brownieList.size() - 1){
                        throw new ProductDoesNotExistException("Este produto não existe no banco de dados!");
                    }
                }
            } catch (ProductDoesNotExistException e) {
                System.out.println(e.getMessage());
            }
            Create create = new Create();
            create.writeFile(brownieList);


        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    public boolean findItem(JSONObject brownie, String brownieName) {
        return Objects.equals(brownie.get("Nome"), brownieName);
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
