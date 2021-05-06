package labos_02.task4.number_generators;

import java.util.ArrayList;
import java.util.List;

public class FibonacciGenerator implements NumberGenerator{
    private int n;
    private List<Integer> numbers;

    public FibonacciGenerator(int n){
        this.n=n;
        this.numbers=new ArrayList<>();
    }

    @Override
    public List<Integer> generate() {
        int first=0,second=1,sum;

        for(int i=0;i<n;i++){
            numbers.add(first);
            sum=first+second;
            first=second;
            second=sum;
        }

        return numbers;
    }

    public List<Integer> getNumbers() {
        List<Integer> copy=new ArrayList<>(this.numbers);
        return copy;
    }
}
