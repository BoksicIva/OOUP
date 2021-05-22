package labos_03.task2.all;



import labos_03.task2.all.action.InsertTextAction;
import labos_03.task2.all.iterator.LinesIterator;
import labos_03.task2.all.location.LocationRange;
import labos_03.task2.all.location.Location;
import labos_03.task2.all.observers.CursorObserver;
import labos_03.task2.all.observers.SelectionObserver;
import labos_03.task2.all.observers.SelectionObserverForButtens;
import labos_03.task2.all.observers.TextObserver;
import labos_03.task2.all.stack.ClipboardStack;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class TextEditorModel implements Iterable<String>{
    private List<String> lines;
    private LocationRange selectionRange;
    private Location cursorLocation;
    private List<CursorObserver> cursorObservers;
    private List<TextObserver> textObservers;
    private List<SelectionObserverForButtens> selectionObserverForButtens;
    private List<SelectionObserver> selectionObservers;
    private ClipboardStack stack=new ClipboardStack();
    private final UndoManager undoManager=UndoManager.getInstance();

    public TextEditorModel(String text){
        List<String> lines = new ArrayList(List.of(text.split("\n")));
        this.lines=lines;
        cursorObservers=new ArrayList<>();
        textObservers=new ArrayList<>();
        selectionObservers=new ArrayList<>();
        selectionObserverForButtens=new ArrayList<>();
        this.cursorLocation=new Location(0,0);
        this.selectionRange=new LocationRange(new Location(cursorLocation.getRow(),cursorLocation.getColumn()),new Location(cursorLocation.getRow(),cursorLocation.getColumn()));
    }

    public UndoManager getUndoManager() {
        return undoManager;
    }

    public Location getCursorLocation() {
        return cursorLocation;
    }

    public void setCursorLocation(Location cursorLocation) {
        this.cursorLocation = cursorLocation;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    /**
     *
     * @return vraća iterator koji prolazi kroz sve retke dokumenta
     */
    public Iterator<String> allLines(){
        return new LinesIterator<String>(this,0, lines.size());
    }

    /**
     *
     * @param index1 pocetak raspona
     * @param index2 kraj raspona
     * @return vraća iterator koji prolazi kroz dani raspon redaka (prvi uključiv, drugi isključiv)
     */
    public Iterator<String> linesRange(int index1, int index2){
        return new LinesIterator<String>(this,index1, index2);
    }


    public void moveCursorLeft(){
        if(cursorLocation.getColumn()!=0){
            cursorLocation.setColumn(cursorLocation.getColumn()-1);

        }else{
            if(cursorLocation.getRow() > 0){
                cursorLocation.setRow(cursorLocation.getRow()-1);
                cursorLocation.setColumn(lines.get(cursorLocation.getRow()).length());
            }else{
                cursorLocation.setRow(lines.size()-1);
                cursorLocation.setColumn(lines.get(cursorLocation.getRow()).length());
            }
        }
        notifyCursorObservers();
    }

    public void moveCursorRight(){
        if(cursorLocation.getColumn() != lines.get(cursorLocation.getRow()).length()){
            cursorLocation.setColumn(cursorLocation.getColumn()+1);
        }else{
            if(cursorLocation.getRow()==lines.size()-1){
                cursorLocation.setRow(0);
                cursorLocation.setColumn(0);
            }else{
                cursorLocation.setColumn(0);
                cursorLocation.setRow(cursorLocation.getRow()+1);
            }
        }
        notifyCursorObservers();
    }

    public void moveCursorUp(){
        if(cursorLocation.getRow()!=0){
            cursorLocation.setRow(cursorLocation.getRow()-1);
            cursorLocation.setColumn(Math.min(cursorLocation.getColumn(),lines.get(cursorLocation.getRow()).length()));
        }else{
            cursorLocation.setRow(0);
            cursorLocation.setColumn(0);
        }
        notifyCursorObservers();
    }

    public void moveCursorDown(){
        if(cursorLocation.getRow()!=lines.size()-1){
            cursorLocation.setRow(cursorLocation.getRow()+1);
            cursorLocation.setColumn(Math.min(cursorLocation.getColumn(),lines.get(cursorLocation.getRow()).length()));
        }else{
            cursorLocation.setColumn(lines.get(cursorLocation.getRow()).length());
        }
        notifyCursorObservers();
    }

    public void deleteBefore(){
        if (cursorLocation.getColumn()==0 && cursorLocation.getRow()!=0){
            String movingRow=lines.get(cursorLocation.getRow());
            lines.remove(cursorLocation.getRow());
            cursorLocation.setColumn(lines.get(cursorLocation.getRow()-1).length());
            cursorLocation.setRow(cursorLocation.getRow()-1);
            String row=lines.get(cursorLocation.getRow());
            lines.set(cursorLocation.getRow(),row+movingRow);
            notifyTextObservers();
            notifyCursorObservers();
        }else if(cursorLocation.getColumn()!=0){
            String row=lines.get(cursorLocation.getRow()).substring(0,cursorLocation.getColumn()-1);
            String row2=lines.get(cursorLocation.getRow()).substring(cursorLocation.getColumn(),lines.get(cursorLocation.getRow()).length());
            lines.set(cursorLocation.getRow(),row+row2);
            moveCursorLeft();
            notifyTextObservers();
            notifyCursorObservers();
        }

    }

    public void deleteAfter(){
        if (cursorLocation.getColumn()==lines.get(cursorLocation.getRow()).length() && cursorLocation.getRow()!=lines.size()-1){
            String movingRow=lines.get(cursorLocation.getRow()+1);
            lines.remove(cursorLocation.getRow()+1);
            String row=lines.get(cursorLocation.getRow());
            lines.set(cursorLocation.getRow(),row+movingRow);
            notifyTextObservers();
            notifyCursorObservers();
        }else if(cursorLocation.getColumn()+1!=lines.get(cursorLocation.getRow()).length()){
            String row1=lines.get(cursorLocation.getRow()).substring(0,cursorLocation.getColumn());
            String row2="";
            if (cursorLocation.getColumn()+2 < lines.get(cursorLocation.getRow()).length())
                row2=lines.get(cursorLocation.getRow()).substring(cursorLocation.getColumn()+1,lines.get(cursorLocation.getRow()).length());
            lines.set(cursorLocation.getRow(),row1+row2);
            notifyTextObservers();
            notifyCursorObservers();
        }

    }

    public void deleteRange(LocationRange range){
        if(range.getStart().getRow()==range.getEnd().getRow()){
            String row1=lines.get(cursorLocation.getRow()).substring(0,range.getStart().getColumn());
            String row2="";
            if (range.getEnd().getColumn()+1 < lines.get(cursorLocation.getRow()).length())
                row2=lines.get(cursorLocation.getRow()).substring(range.getEnd().getColumn(),lines.get(cursorLocation.getRow()).length());
            lines.set(cursorLocation.getRow(),row1+row2);
        }else{
            String row1=lines.get(range.getStart().getRow()).substring(0,range.getStart().getColumn());
            String row2="";
            if (range.getEnd().getColumn()+2 < lines.get(range.getEnd().getRow()).length())
                row2=lines.get(cursorLocation.getRow()).substring(range.getEnd().getColumn(),lines.get(cursorLocation.getRow()).length());
            lines.set(cursorLocation.getRow(),row1+row2);
            for(int j=range.getStart().getRow(),i=range.getStart().getRow();i<range.getEnd().getRow();i++){
                lines.remove(j);
            }

        }
        cursorLocation=new Location(range.getStart());
        range.setEnd(new Location(range.getStart()));
        notifyTextObservers();
        notifyCursorObservers();
    }

    public LocationRange getSelectionRange(){
        return selectionRange;
    }

    public void setSelectionRange(LocationRange range){
        this.selectionRange=range;
        notifySelectionObservers();
        notifySelectionObserversForButtens();
    }

    public void insert(char c){
//        if(!selectionRange.getStart().equals(selectionRange.getEnd()))
//            deleteRange(selectionRange);
        if(c== KeyEvent.VK_ENTER){
            String row1 = lines.get(cursorLocation.getRow()).substring(0, cursorLocation.getColumn() );
            String row2 = lines.get(cursorLocation.getRow()).substring(cursorLocation.getColumn() , lines.get(cursorLocation.getRow()).length());
            lines.set(cursorLocation.getRow(),row1);
            lines.add(cursorLocation.getRow()+1,row2);
            cursorLocation.setColumn(0);
            moveCursorDown();
        }else {
            String row1 = lines.get(cursorLocation.getRow()).substring(0, cursorLocation.getColumn() );
            String row2 = lines.get(cursorLocation.getRow()).substring(cursorLocation.getColumn(), lines.get(cursorLocation.getRow()).length());
            lines.set(cursorLocation.getRow(), row1 + c + row2);
            moveCursorRight();
        }
        notifyTextObservers();
    }

    public void insert(String text){
//        if(!selectionRange.getStart().equals(selectionRange.getEnd()))
//            deleteRange(selectionRange);
        String row1=lines.get(cursorLocation.getRow()).substring(0,cursorLocation.getColumn());
        String row2=lines.get(cursorLocation.getRow()).substring(cursorLocation.getColumn(),lines.get(cursorLocation.getRow()).length());
        lines.set(cursorLocation.getRow(),row1+text+row2);
        cursorLocation.setColumn((row1+text).length());
        notifyTextObservers();

    }

    public String selectedText(){
        Location start=selectionRange.getStart();
        Location end=selectionRange.getEnd();
        if(start.getRow()==end.getRow()){
            return lines.get(start.getRow()).substring(start.getColumn(),end.getColumn());
        }else{
            String text="";
            text+=lines.get(start.getRow()).substring(start.getColumn())+"\n";
            for(int i=start.getRow()+1;i<end.getRow();i++){
                text+=lines.get(i)+"\n";
            }
            text+=lines.get(end.getRow()).substring(0,end.getColumn());
            return text;
        }
    }

    public void clear(){
        deleteRange(new LocationRange(new Location(0,0),new Location(lines.size()-1,lines.get(lines.size()-1).length())));
        cursorLocation=new Location(0,0);
        selectionRange=new LocationRange(new Location(0,0),new Location(0,0));
    }

    public void shiftDown(){
//        selectionRange.getStart().setColumn(cursorLocation.getColumn());
//        selectionRange.getStart().setRow(cursorLocation.getRow());
        if(cursorLocation.getRow()!=lines.size()-1){
            selectionRange.getEnd().setColumn(Math.min(lines.get(cursorLocation.getRow()+1).length(),cursorLocation.getColumn()));
            selectionRange.getEnd().setRow(cursorLocation.getRow()+1);
        }else{
            selectionRange.getEnd().setColumn(lines.get(lines.size()-1).length());
        }
        moveCursorDown();
        notifySelectionObservers();
        notifySelectionObserversForButtens();
    }

    public void shiftUp(){
//        selectionRange.getEnd().setColumn(cursorLocation.getColumn());
//        selectionRange.getEnd().setRow(cursorLocation.getRow());
        if(cursorLocation.getRow()!=0){
            selectionRange.getStart().setColumn(Math.min(lines.get(cursorLocation.getRow()-1).length(),cursorLocation.getColumn()));
            selectionRange.getStart().setRow(cursorLocation.getRow()-1);
        }else{
            selectionRange.getStart().setColumn(0);
            selectionRange.getStart().setRow(0);
        }
        moveCursorUp();
        notifySelectionObservers();
        notifySelectionObserversForButtens();
    }

    public void shiftLeft(){
//        selectionRange.getEnd().setColumn(cursorLocation.getColumn());
//        selectionRange.getEnd().setRow(cursorLocation.getRow());
        if(cursorLocation.equals(selectionRange.getEnd())) {
            if (cursorLocation.getColumn() != 0) {
                selectionRange.getEnd().setColumn(cursorLocation.getColumn() - 1);
            } else if (cursorLocation.getRow() != 0) {
                selectionRange.getEnd().setColumn(lines.get(cursorLocation.getRow() - 1).length());
                selectionRange.getEnd().setRow(cursorLocation.getRow() - 1);
            } else {
                selectionRange.getEnd().setColumn(0);
                selectionRange.getEnd().setRow(0);
            }
        }else{
            if (cursorLocation.getColumn() != 0) {
                selectionRange.getStart().setColumn(cursorLocation.getColumn() - 1);
            } else if (cursorLocation.getRow() != 0) {
                selectionRange.getStart().setColumn(lines.get(cursorLocation.getRow() - 1).length());
                selectionRange.getStart().setRow(cursorLocation.getRow() - 1);
            } else {
                selectionRange.getStart().setColumn(0);
                selectionRange.getStart().setRow(0);
            }
        }
        moveCursorLeft();
        notifySelectionObservers();
        notifySelectionObserversForButtens();
    }

    public void shiftRight(){
//        selectionRange.getStart().setColumn(cursorLocation.getColumn());
//        selectionRange.getStart().setRow(cursorLocation.getRow());
        if(cursorLocation.equals(selectionRange.getEnd())) {
            if (cursorLocation.getColumn() != lines.get(cursorLocation.getRow()).length()) {
                selectionRange.getEnd().setColumn(cursorLocation.getColumn() + 1);
                selectionRange.getEnd().setRow(cursorLocation.getRow());
            } else if (cursorLocation.getRow() != lines.size() - 1) {
                selectionRange.getEnd().setColumn(0);
                selectionRange.getEnd().setRow(cursorLocation.getRow() + 1);

            } else {
                selectionRange.getEnd().setColumn(lines.get(lines.size() - 1).length());
            }
        }else {
            if (cursorLocation.getColumn() != lines.get(cursorLocation.getRow()).length()) {
                selectionRange.getStart().setColumn(cursorLocation.getColumn() + 1);
                selectionRange.getStart().setRow(cursorLocation.getRow());
            } else if (cursorLocation.getRow() != lines.size() - 1) {
                selectionRange.getStart().setColumn(0);
                selectionRange.getStart().setRow(cursorLocation.getRow() + 1);

            } else {
                selectionRange.getStart().setColumn(lines.get(lines.size() - 1).length());
            }
        }
        moveCursorRight();
        notifySelectionObservers();
        notifySelectionObserversForButtens();
    }




    public void addCursorObserver(CursorObserver c){
        this.cursorObservers.add(c);
    }

    public void removeCursorObserver(CursorObserver c){
        this.cursorObservers.remove(c);
    }

    public void notifyCursorObservers(){
        for (CursorObserver  observer: cursorObservers ) {
            observer.updateCursorLocation(this.cursorLocation);
        }
        //selectionRange.setStart(new Location(cursorLocation.getRow(),cursorLocation.getColumn()));
    }

    public void addTextObserver(TextObserver c){
        this.textObservers.add(c);
    }

    public void removeTextObserver(TextObserver c){
        this.textObservers.remove(c);
    }

    public void notifyTextObservers(){
        for (TextObserver  observer: textObservers ) {
            observer.updateText();

        }
    }

    public void addSelectionObserver(SelectionObserver c){
        this.selectionObservers.add(c);
    }

    public void removeSelectionObserver(SelectionObserver c){
        this.selectionObservers.remove(c);
    }

    public void notifySelectionObservers(){
        for (SelectionObserver observer: selectionObservers ) {
            observer.updateSelection();

        }
    }

    public void addSelectionObserverForButtens(SelectionObserverForButtens c){
        this.selectionObserverForButtens.add(c);
    }

    public void removeSelectionObserverForButtens(SelectionObserverForButtens c){
        this.selectionObserverForButtens.remove(c);
    }

    public void notifySelectionObserversForButtens(){
        for (SelectionObserverForButtens observer: selectionObserverForButtens ) {
            observer.updateButtons();

        }
    }


    @Override
    public Iterator<String> iterator() {
        return new LinesIterator<String>(this,0,lines.size());
    }



}