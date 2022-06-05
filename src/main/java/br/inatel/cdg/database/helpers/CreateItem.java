package br.inatel.cdg.database.helpers;
import br.inatel.cdg.database.create.Create;
import br.inatel.cdg.database.exceptions.InvalidPriceException;

import java.util.Scanner;

public class CreateItem {

    public void CreateBrownie(){

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Entre com as informações do brownie: Nome, preço, tipo, quantidade" +
                    " separando cada uma por espaço");
            String brownieName = scanner.nextLine();
            String brownieValue = scanner.nextLine();
            String brownieType = scanner.nextLine();
            String brownieQuantity = scanner.nextLine();
            if(!brownieValue.contains(",")) {
                Create criar = new Create(brownieName, Double.parseDouble(brownieValue), brownieType,
                        Integer.parseInt(brownieQuantity));
            }
            else {
                throw new InvalidPriceException("Entrada inválida! Insira o preço separado por .");
            }
        } catch (InvalidPriceException e){
            System.out.println(e.getMessage());
        }
    }
}
