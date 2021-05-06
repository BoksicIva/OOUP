package labos_02.task5.promatraci;

import labos_02.task5.SlijedBrojeva;

import java.util.ArrayList;

public class IspisMedijana implements Promatrac{

    @Override
    public void update(SlijedBrojeva sb) {
        int medijan=0;
        ArrayList<Integer> kolekcija=sb.getKolekcijaBrojeva();
        int n=kolekcija.size();
        if (n%2==0)
            medijan=(kolekcija.get(n/2-1)+kolekcija.get(n/2))/2;
        else
            medijan=kolekcija.get((n+1)/2-1);
        System.out.println("Medijan brojeva u kolekciji je : "+medijan);
    }
}
