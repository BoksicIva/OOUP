package labos_02.task4.number_generators;

import java.util.ArrayList;
import java.util.List;

public class SequentialGenerator implements NumberGenerator{
    private int start;
    private int end;
    private int step;
    private List<Integer> numbers;

    public SequentialGenerator(int start, int end, int step){
        this.start=start;
        this.end=end;
        this.step=step;
        this.numbers=new ArrayList<>();
    }


    @Override
    public List<Integer> generate() {
        for(int i=start;i<=end;i=i+step)
            numbers.add(i);
        return numbers;
    }

    @Override
    public List<Integer> getNumbers() {
        List<Integer> copy=new ArrayList<>(this.numbers);
        return copy;
    }

}
