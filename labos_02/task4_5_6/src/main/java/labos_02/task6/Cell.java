package labos_02.task6;

import labos_02.task6.operation.Operation;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private String exp;
    private Integer value;
    private List<Cell> cellObservers;
    private Operation operation;
    private List<Cell> cellsInexpresion=new ArrayList<>();
    private Sheet sheet;



    public Cell(Sheet sheet) {
        this.sheet=sheet;
        this.cellObservers=new ArrayList<>();
    }

    public Cell(Sheet sheet,String exp, Integer value) {
        this(sheet);
        setExp(exp);
    }

    public String getExp() {
        return exp;
    }

    public Integer getValue() {
        return value;
    }

    public List<Cell> getCellObservers() {
        return cellObservers;
    }

    public List<Cell> getCellsInexpresion() {
        return cellsInexpresion;
    }

    public void setExp(String exp) {
        String[] cells = new String[0];

        if(this.exp==null){
            this.exp=exp;
            this.value=0;
        }
        else {
            if (this.exp != "" && this.exp != null) {
                for(Operation operation : this.sheet.getOperations())
                    if (this.exp.contains(operation.getSign())) {
                        cells = this.exp.split("\\"+operation.getSign());
                        this.operation=operation;
                        for (String cell : cells)
                            this.sheet.cell(cell).removeCellObserver(this);
                    }

            }
            if (isInteger(exp)) {
                setValue(Integer.parseInt(exp));
            } else {
                boolean found=false;
                for(Operation operation : this.sheet.getOperations())
                    if (exp.contains(operation.getSign())) {
                        this.operation=operation;
                        cells = exp.split( "\\"+operation.getSign());
                        found=true;
                        }
                if(!found){
                    cells=new String[1];
                    cells[0]=exp;
                }
                cellsInexpresion.clear();
                for (String cell : cells) {
                    if (this.sheet.cell(cell).getCellsInexpresion().contains(this))
                        throw new RuntimeException("Two cells depend on each other.");
                    else{
                        this.sheet.cell(cell).addCellObserver(this);
                        cellsInexpresion.add(this.sheet.cell(cell));
                    }
                }
                updateValue();
            }

            this.exp = exp;
            notifyObservers();
        }
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    private void updateValue(){
        if(this.operation!=null) {
            int value = this.operation.generate(this);
            this.setValue(value);
        }else{
            this.setValue(this.value);
        }
    }


    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public void addCellObserver(Cell c) {
        cellObservers= new ArrayList<>(cellObservers);
        cellObservers.add(c);
    }

    public void removeCellObserver(Cell c) {
        cellObservers= new ArrayList<>(cellObservers);
        cellObservers.remove(c);
    }

    public void notifyObservers(){
        for(Cell observer : cellObservers)
            observer.updateValue();
    }
}
