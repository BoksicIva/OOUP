package labos_02.task4.number_generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NormalDistibutionGenerator implements NumberGenerator{
    private int mean;
    private int variance;
    private int n;
    private List<Integer> numbers;

    public NormalDistibutionGenerator(int mean,int variance,int n){
        this.mean=mean;
        this.variance=variance;
        this.n=n;
        this.numbers=new ArrayList<>();
    }


    @Override
    public List<Integer> generate() {
        Random random = new Random();
        for(int i=0;i<n;i++){
            int randomNumb= (int) (mean + random.nextGaussian() * variance);
            numbers.add(randomNumb);
        }
        return numbers;
    }

    public List<Integer> getNumbers() {
        List<Integer> copy=new ArrayList<>(this.numbers);
        return copy;
    }
}
