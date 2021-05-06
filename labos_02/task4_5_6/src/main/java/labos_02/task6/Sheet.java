package labos_02.task6;

import labos_02.task6.operation.Operation;
import labos_02.task6.operation.Sum;

import java.util.ArrayList;
import java.util.List;

public class Sheet {
    private Cell[][] array;
    private int rows;
    private int columns;
    private List<Operation> operations;

    public List<Operation> getOperations() {
        return operations;
    }

    public Sheet(int rows, int columns){
        this.rows=rows;
        this.columns=columns;
        this.operations=new ArrayList<>();
        this.array=new Cell[rows][columns];
        for(int i=0;i<rows;i++)
            for(int j=0;j<columns;j++)
                array[i][j]=new Cell(this,"",0);
    }

    public void addOperation(Operation operation) {
        this.operations.add(operation);
    }

    public Cell cell(String ref) {
        int r=Character.getNumericValue(ref.charAt(0))-Character.getNumericValue('A');
        int c=Character.getNumericValue(ref.charAt(1))-1;
        try {
            return array[r][c];
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("There is no such element in sheet.");
        }
    }

    public void set(String ref,String content){
        int r=Character.getNumericValue(ref.charAt(0))-Character.getNumericValue('A');
        int c=Character.getNumericValue(ref.charAt(1))-1;
        array[r][c].setExp(content);
    }

    public List<Cell>  getrefs(Cell cell){
        return cell.getCellsInexpresion();
    }

    public void print(){
        for(int i=0;i<rows;i++) {
            for (int j = 0; j < columns; j++)
                System.out.format("|%-6s(%2s) |", array[i][j].getExp(), array[i][j].getValue());
            System.out.println();
        }
    }


    public static void main(String args[]){

        Sheet s=new Sheet(5,5);
        s.addOperation(new Sum());
        s.set("A1","2");
        s.set("A2","5");
        s.set("A3","A1+A2");
        s.set("A2","7");
        s.print();
        System.out.println();

        s.set("A1","4");
        s.set("A4","A1+A3");
        s.print();
        System.out.println();
        try{
        s.set("A1","A3");
        } catch (RuntimeException e) {
            System.out.println("Caught exception: "+ e+"\n");
        }
        s.print();

    }
}
