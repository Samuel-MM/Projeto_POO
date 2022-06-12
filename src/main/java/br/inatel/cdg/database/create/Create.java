package br.inatel.cdg.database.create;

import br.inatel.cdg.database.brownie.Brownie;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Create extends Brownie {

    String fileName = "database.txt";

    public Create(String name, double price, String type, int quantity){
        super(name, price, type, quantity);
        salvarBrownie();
    }

    @Override
    public double getFinalPriceUnitary(){
        return price * 0.1 + price;
    }

    public void salvarBrownie(){
        Path file = Paths.get(fileName);
        try{
            //Id - Nome - Tipo - Quantidade - Preço Unitário - Preço Final Total

            String brownieTexto =
                    "Id: " + (Files.lines(file).count() + 1) +
                            " - Nome: " + name +
                            " - Tipo: " + type +
                            " - Quantidade: " + quantity +
                            " - Preço final unidade: " + String.format("%.2f", getFinalPriceUnitary()) +
                            " - Preço final total: " + String.format("%.2f", getFinalPriceTotal());

            //If file is empty write the first line
            if(Files.lines(file).findAny().isEmpty()){
                Files.writeString(file, brownieTexto);
            }
            //If there's content append the new line to te end of the file
            else {
                String newLine = System.getProperty("line.separator") + brownieTexto;
                Files.write(file, newLine.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            }
            getInfo();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public double getFinalPriceTotal(){
        return getFinalPriceUnitary() * quantity;
    }
    
    // polimorfismo
    @Override
    public void getInfo(){
        System.out.println("O seu brownie foi criado: " + name + " - " + type + " - R$" +
                String.format("%.2f", price) + " - " + quantity);
    }

}
