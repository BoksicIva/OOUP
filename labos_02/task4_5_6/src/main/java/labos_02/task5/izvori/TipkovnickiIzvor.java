package labos_02.task5.izvori;

import java.util.Scanner;

public class TipkovnickiIzvor implements Izvor{
    private Scanner scanner = new Scanner(System.in);
    
    @Override
    public int procitajBroj() {
        int broj=-1;
        if(scanner.hasNextInt())
           broj=scanner.nextInt();
        return broj;
    }
}
