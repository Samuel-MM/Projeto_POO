import br.inatel.cdg.database.delete.Delete;
import br.inatel.cdg.database.helpers.CreateItem;
import br.inatel.cdg.database.read.Read;
import br.inatel.cdg.database.update.Update;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Seja bem vindo a nossa loja!");
        System.out.println("Selecione a operação: 'C' para Adicionar um novo item");
        System.out.println("Selecione a operação: 'R' para Ler um item");
        System.out.println("Selecione a operação: 'U' para Atualizar um item");
        System.out.println("Selecione a operação: 'D' para Deletar um item");

        String userInput = scanner.next();
        
        while(!Objects.equals(userInput, "E")) {
            switch (userInput.toUpperCase()) {
                case "C":
                    CreateItem createItem = new CreateItem();
                    createItem.createBrownie();
                    break;
                case "R":
                    Read readItem = new Read();
                    readItem.readBrownie();
                    break;
                case "U":
                    Update updateItem = new Update();
                    Scanner productName = new Scanner(System.in);
                    System.out.println("Entre com o nome do produto a ser editado");
                    updateItem.selectItem(productName.nextLine());
                    break;
                case "D":
                    System.out.println("Entre com o nome do brownie a ser deletado");
                    Scanner entrada = new Scanner(System.in);

                    Delete deleteItem = new Delete();
                    deleteItem.selectItem(entrada.nextLine());
                    break;
                default:
                    System.out.println("Insira uma opção válida!");
            }
            System.out.println("Deseja realizar outra operação? Caso queira sair digite 'E'");
            userInput = scanner.next();
        }
        // close the scanner
        scanner.close();

    }
}