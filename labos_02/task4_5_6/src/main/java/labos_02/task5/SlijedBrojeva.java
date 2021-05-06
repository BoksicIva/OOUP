package labos_02.task5;
import labos_02.task5.izvori.DatotecniIzvor;
import labos_02.task5.izvori.Izvor;
import labos_02.task5.izvori.TipkovnickiIzvor;
import labos_02.task5.promatraci.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SlijedBrojeva {

    private ArrayList<Integer> kolekcijaBrojeva;
    private Izvor izvor;
    private ArrayList<Promatrac> promatraci;

    public SlijedBrojeva(Izvor izvor){
        this.kolekcijaBrojeva=new ArrayList<>();
        this.izvor=izvor;
        this.promatraci=new ArrayList<Promatrac>();
    }

    public void setIzvor(Izvor izvor) {
        this.izvor = izvor;
    }

    public ArrayList<Integer> getKolekcijaBrojeva() {
        return kolekcijaBrojeva;
    }

    public void kreni() throws InterruptedException {
        int broj;
        while(true){
            broj= izvor.procitajBroj();
            if(broj == -1)
                break;
            kolekcijaBrojeva.add(broj);
            obavijestiPromatrace();
            Thread.sleep(1000);
        }
    }

    public void dodajPromatraca(Promatrac p){
        promatraci.add(p);
    }

    public void ukloniPromatraca(Promatrac p){
        promatraci.remove(p);
    }

    private void obavijestiPromatrace(){
        for(Promatrac p : promatraci){
            p.update(this);
        }
    }

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {

        Promatrac promatrac1=new IspisDatuma();
        Promatrac promatrac2=new IspisProsjeka();
        Promatrac promatrac3=new IspisSume();
        Promatrac promatrac4=new IspisMedijana();

        Izvor izvor=new TipkovnickiIzvor();

        SlijedBrojeva sb1=new SlijedBrojeva(izvor);
        sb1.dodajPromatraca(promatrac1);
        sb1.dodajPromatraca(promatrac2);
        sb1.dodajPromatraca(promatrac3);
        sb1.dodajPromatraca(promatrac4);

        sb1.kreni();

        sb1.ukloniPromatraca(promatrac4);
        sb1.setIzvor(new DatotecniIzvor("C:\\FER\\OOUP - java intelli\\datotecniIzvor.txt"));
        sb1.kreni();

//        Promatrac promatrac1=new IspisDatuma();
//        Promatrac promatrac2=new IspisProsjeka();
//        Promatrac promatrac3=new IspisSume();
//        Promatrac promatrac4=new IspisMedijana();
//
//        Izvor izvor=new DatotecniIzvor("C:\\FER\\OOUP - java intelli\\datotecniIzvor.txt");
//
//        SlijedBrojeva sb1=new SlijedBrojeva(izvor);
//        sb1.dodajPromatraca(promatrac1);
//        sb1.dodajPromatraca(promatrac2);
//        sb1.dodajPromatraca(promatrac3);
//        sb1.dodajPromatraca(promatrac4);
//
//        sb1.kreni();

    }

}


