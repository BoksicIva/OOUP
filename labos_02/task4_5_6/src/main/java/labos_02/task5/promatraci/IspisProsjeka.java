package labos_02.task5.promatraci;

import labos_02.task5.SlijedBrojeva;

public class IspisProsjeka implements Promatrac{
    @Override
    public void update(SlijedBrojeva sb) {
        int suma=0;
        for(int broj : sb.getKolekcijaBrojeva())
            suma+=broj;
        System.out.println("Prosjek brojeva u kolekciji je : "+suma*1.0/sb.getKolekcijaBrojeva().size());
    }
}
