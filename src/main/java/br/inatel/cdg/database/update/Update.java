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
        // trycatch para evitar quebra no codigo caso haja erros
        try (FileReader reader = new FileReader(database)) {
            // banco de dados em json
            Object obj = jsonParser.parse(reader);

            JSONArray brownieList = (JSONArray) obj;

            try {
                for (int i = 0; i < brownieList.size(); i++) {
                    brownieExists = findItem((JSONObject) brownieList.get(i), brownieName);
                    if (brownieExists) {
                        System.out.println("Entre com o parâmetro a ser editado");
                        updateBrownieInfo((JSONObject) brownieList.get(i), UpdateInput.nextLine());
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
        if(Objects.equals(parameter, "Preço final total")) {
            System.out.println("Entre com o novo valor");
            brownie.put(parameter, UpdateInput.nextLine().replace(".", ","));
            getInfo();
        }
        else if(Objects.equals(parameter, "Preço")){
            System.out.println("Entre com o novo valor");
            updatePrice(brownie, parameter);
            getInfo();
        } else if(Objects.equals(parameter, "Quantidade")) {
            System.out.println("Entre com o novo valor");
            updateQuantity(brownie, parameter);
            getInfo();
        }else if(Objects.equals(parameter, "Tipo")){
            System.out.println("Entre com o novo valor");
            brownie.put(parameter, UpdateInput.nextLine());
            getInfo();
        }else if(Objects.equals(parameter, "Nome")) {
            System.out.println("Entre com o novo valor");
            brownie.put(parameter, UpdateInput.nextLine());
            getInfo();
        }else {
            System.out.println("Insira um parâmetro válido!");
        }
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
        System.out.println("Você atualizou com sucesso!");
    }

    private void updateQuantity(JSONObject brownie, String parameter){
        quantity = Integer.parseInt(UpdateInput.nextLine().replace(",", "."));
        price = Double.parseDouble(brownie.get("Preço").toString().replace(",", "."));
        brownie.put(parameter, Integer.toString(quantity));
        brownie.put("Preço final total", Double.toString(getFinalPriceTotal()).replace(".", ","));
    }

    private void updatePrice(JSONObject brownie, String parameter){
        price = Double.parseDouble(UpdateInput.nextLine().replace(".", ",").replace(",", "."));
        quantity = Integer.parseInt(brownie.get("Quantidade").toString().replace(",", "."));
        brownie.put(parameter, Double.toString(price).replace(".", ","));
        brownie.put("Preço final unidade", Double.toString(getFinalPriceUnitary()).replace(".", ","));
        brownie.put("Preço final total", Double.toString(getFinalPriceTotal()).replace(".", ","));
    }
}
