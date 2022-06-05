import br.inatel.cdg.database.helpers.CreateItem;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Seja bem vindo a nossa loja! Selecione a operação: 'C' para Adicionar um novo item");
        String userInput = scanner.next();
        switch (userInput){
            case "C":
                CreateItem createItem = new CreateItem();
                createItem.CreateBrownie();
                break;
        }

    }
}
