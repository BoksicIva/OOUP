package labos_03.task2.all.iterator;

import labos_03.task2.all.TextEditorModel;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinesIterator<String> implements Iterator<String> {
    private TextEditorModel model;
    private int start;
    private int end;

    public LinesIterator(TextEditorModel model, int start, int end){
        this.model=model;
        this.start=start;
        this.end=end;
        if(end > model.getLines().size()) throw new IndexOutOfBoundsException();
    }

    @Override
    public boolean hasNext() {
        return start < end;
    }

    @Override
    public String next() {
        if(!hasNext())
            throw new NoSuchElementException();
        int oldStart=start;
        start++;
        return (String) model.getLines().get(oldStart);
    }
}