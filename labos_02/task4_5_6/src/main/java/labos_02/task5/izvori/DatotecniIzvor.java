package labos_02.task5.izvori;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DatotecniIzvor implements Izvor{
    private String put;
    private Scanner scanner;

    public DatotecniIzvor(String put) throws FileNotFoundException {
        this.put=put;
        this.scanner = new Scanner(new File(put));
    }


    @Override
    public int procitajBroj(){
        int broj=-1;
        if(scanner.hasNextInt())
            broj=scanner.nextInt();
        return broj;
    }
}
