package br.inatel.cdg.database.update;

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

public class Update extends Brownie implements ManipulateData {

    @Override
    public void selectItem(String brownieName){

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(database)) {

            Object obj = jsonParser.parse(reader);

            JSONArray brownieList = (JSONArray) obj;

            try {
                for (int i = 0; i < brownieList.size(); i++) {
                    brownieExists = findItem((JSONObject) brownieList.get(i), brownieName);
                    if (brownieExists) {
                        System.out.println("Entre com o parâmetro a ser editado");
                        updateBrownieInfo((JSONObject) brownieList.get(i), entradaUpdate.nextLine());
                        break;
                    } else if (i == brownieList.size() - 1) {
                        throw new ProductDoesNotExistException("Este produto não existe no banco de dados!");
                    }
                }
            } catch (ProductDoesNotExistException e){
                System.out.println(e.getMessage());
            }
            Create create = new Create();
            create.writeFile(brownieList);


        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean findItem(JSONObject brownie, String brownieName) {
        return Objects.equals(brownie.get("Nome"), brownieName);
    }

    private void updateBrownieInfo(JSONObject brownie, String parameter) {
        System.out.println("Entre com o novo valor");
        if(Objects.equals(parameter, "Preço final total")) {
            brownie.put(parameter, entradaUpdate.nextLine().replace(".", ","));
        }
        else if(Objects.equals(parameter, "Preço")){
            updatePrice(brownie, parameter);
        } else if(Objects.equals(parameter, "Quantidade")){
            updateQuantity(brownie, parameter);
        }
        else {
            brownie.put(parameter, entradaUpdate.nextLine());
        }
        getInfo();
    }


    @Override
    public double getFinalPriceUnitary() {
        return price * 0.1 + price;
    }

    @Override
    public double getFinalPriceTotal() {
        return getFinalPriceUnitary() * quantity;
    }

    public void getInfo(){
        System.out.println("O item foi atualizado com sucesso!");
    }

    private void updateQuantity(JSONObject brownie, String parameter){
        quantity = Integer.parseInt(entradaUpdate.nextLine().replace(",", "."));
        price = Double.parseDouble(brownie.get("Preço").toString().replace(",", "."));
        brownie.put(parameter, Integer.toString(quantity));
        brownie.put("Preço final total", Double.toString(getFinalPriceTotal()).replace(".", ","));
    }

    private void updatePrice(JSONObject brownie, String parameter){
        price = Double.parseDouble(entradaUpdate.nextLine().replace(".", ",").replace(",", "."));
        quantity = Integer.parseInt(brownie.get("Quantidade").toString().replace(",", "."));
        brownie.put(parameter, Double.toString(price).replace(".", ","));
        brownie.put("Preço final unidade", Double.toString(getFinalPriceUnitary()).replace(".", ","));
        brownie.put("Preço final total", Double.toString(getFinalPriceTotal()).replace(".", ","));
    }
}
