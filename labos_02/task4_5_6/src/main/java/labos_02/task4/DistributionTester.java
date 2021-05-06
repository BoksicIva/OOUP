package labos_02.task4;
import labos_02.task4.number_generators.FibonacciGenerator;
import labos_02.task4.number_generators.NormalDistibutionGenerator;
import labos_02.task4.number_generators.NumberGenerator;
import labos_02.task4.number_generators.SequentialGenerator;
import labos_02.task4.percentiles.LinearInterpolation;
import labos_02.task4.percentiles.NearestRank;
import labos_02.task4.percentiles.PercentileMethod;

import java.util.ArrayList;
import java.util.List;

public class DistributionTester {

    private NumberGenerator numberGenerator;
    private PercentileMethod percentileMethod;

    public DistributionTester(NumberGenerator numberGenerator,PercentileMethod percentileMethod){
        this.numberGenerator=numberGenerator;
        this.percentileMethod=percentileMethod;
    }

    public void setNumberGenerator(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public void setPercentileMethod(PercentileMethod percentileMethod) {
        this.percentileMethod = percentileMethod;
    }

    public List<Integer> generate(){
        return this.numberGenerator.generate();
    }

    public List<Integer> getPercentile(List<Integer> numbers){
        List<Integer> percentils=new ArrayList<>();
        for(int i=10;i<100;i+=10)
          percentils.add(this.percentileMethod.getPercentile(i,numbers));
        return percentils;
    }

    public void printNumbers(){
        List<Integer> numbers =this.numberGenerator.getNumbers();
        for(Integer number : numbers)
            System.out.print(number+" ");
        System.out.println();
    }


    public static void main(String args[]){
        DistributionTester dt=new DistributionTester(new NormalDistibutionGenerator(50,10,10),new NearestRank());

        List<Integer> normalNum=dt.generate();
        List<Integer> percentil1=dt.getPercentile(normalNum);
        dt.setPercentileMethod(new LinearInterpolation());
        List<Integer> percentil12=dt.getPercentile(normalNum);
        System.out.println("Normal distribution numbers with parameters N(50,10) : ");
        dt.printNumbers();
        System.out.println(percentil1);
        System.out.println(percentil12+"\n");

        dt.setNumberGenerator(new FibonacciGenerator(10));
        dt.setPercentileMethod(new NearestRank());
        List<Integer> fibonNum=dt.generate();
        List<Integer> percentil21=dt.getPercentile(fibonNum);
        dt.setPercentileMethod(new LinearInterpolation());
        List<Integer> percentil22=dt.getPercentile(fibonNum);
        System.out.println("Fibonacci's first 10 numbers : ");
        dt.printNumbers();
        System.out.println(percentil21);
        System.out.println(percentil22+"\n");

        dt.setNumberGenerator(new SequentialGenerator(38,56,2));
        dt.setPercentileMethod(new NearestRank());
        List<Integer> seqNum=dt.generate();
        List<Integer> percentil31=dt.getPercentile(seqNum);
        dt.setPercentileMethod(new LinearInterpolation());
        List<Integer> percentil32=dt.getPercentile(seqNum);
        System.out.println("Sequential nummbers that starts with 38, ends with 56 and have step 2 : ");
        dt.printNumbers();
        System.out.println(percentil31);
        System.out.println(percentil32+"\n");



    }
}
