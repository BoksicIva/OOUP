package labos_02.task6.operation;

import labos_02.task6.Cell;

import java.util.List;

public class Sum implements Operation{
    @Override
    public String getSign() {
        return "+";
    }

    @Override
    public int generate(Cell cell) {
        int sum=0;
        for(Cell c: cell.getCellsInexpresion())
            sum+=c.getValue();
        return cell.getCellsInexpresion().size()==0? cell.getValue(): sum;
    }
}
