package labos_02.task5.promatraci;

import labos_02.task5.SlijedBrojeva;

public class IspisSume implements Promatrac{

    @Override
    public void update(SlijedBrojeva sb) {
        int suma=0;
        for(int broj : sb.getKolekcijaBrojeva())
            suma+=broj;
        System.out.println("Suma brojeva u kolekciji je : "+suma);
    }
}
