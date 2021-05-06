package labos_02.task4.percentiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NearestRank implements PercentileMethod{

    @Override
    public int getPercentile(int p, List<Integer> numbers) {
        List<Integer> sortedNumbers=new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        int n= (int) Math.ceil(p*numbers.size()/100);
        return sortedNumbers.get(n-1);
    }
}
