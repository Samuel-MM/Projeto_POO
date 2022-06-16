import br.inatel.cdg.database.delete.Delete;
import br.inatel.cdg.database.helpers.CreateItem;
import br.inatel.cdg.database.read.Read;
import br.inatel.cdg.database.update.Update;
import br.inatel.cdg.database.delete.Delete;
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
        switch (userInput.toUpperCase()){
            case "C":
                CreateItem createItem = new CreateItem();
                createItem.CreateBrownie();
                break;
            case "R":
                Read readItem = new Read(userInput, 0, userInput, 0);

                break;
            case "U":
                Update updateItem = new Update();

                break;
            case "D":
                Delete DeleteItem = new Delete();
                break;
        }

    }
}