package labos_02.task4.percentiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * kao interpoliranu vrijednost između elemenata v[i] i v[i+1] za čije percentilne položaje vrijedi p(v[i]) < p < p(v[i+1]);
 * percentilni položaj elementa v_i na rednom broju i računamo kao p(v[i]) = 100*(i-0.5)/N, gdje N odgovara broju elemenata,
 * a redni broj i počinje od jedan; traženu interpoliranu vrijednost v(p) za zadani percentil p određujemo izrazom
 * v(p) = v[i] + N * (p-p(v[i]))*(v[i+1]-v[i])/100; za percentile koji su manji od p(v[1]) odnosno veći od p(v[N])
 * vraćamo v[1] odnosno v[N]; primjerice, 80. percentil niza (1,10,50) bi u tom slučaju bio 46
 */
public class LinearInterpolation implements PercentileMethod{

    @Override
    public int getPercentile(int p, List<Integer> numbers) {
        List<Integer> sortedNumbers=new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);

        int size=sortedNumbers.size();
        if( p >= 100*(size-0.5)/size)
            return sortedNumbers.get(size-1);
        if( p <= 100 *0.5 /size)
            return sortedNumbers.get(0);

        if (size ==1)
            return sortedNumbers.get(0);

        double p_vi = 0,p_vi1;
        int i1 = 0;
        for(int i=1;i<size;i++){
            p_vi=100*(i-0.5)/size;
            p_vi1=100*(i+0.5)/size;
            i1=i;
            if(p_vi<p && p_vi1>p)
                break;
        }

        int vp = (int) (sortedNumbers.get(i1-1) + size * (p-p_vi)*(sortedNumbers.get(i1)-sortedNumbers.get(i1-1))/100);
        return vp;
    }
}
