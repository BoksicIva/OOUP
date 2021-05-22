package labos_03.task2.all.stack;
import labos_03.task2.all.observers.ClipboardStackObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ClipboardStack {
    private Stack<String> texts;
    private List<ClipboardStackObserver> observers;

    public ClipboardStack() {
        texts = new Stack<>();
        observers = new ArrayList<>();
    }

    public String push(String text){
        String text2=texts.push(text);
        notifyClipboardObservers();
        return text2;
    }

    public String pop(){
        String text=texts.pop();
        notifyClipboardObservers();
        return text;
    }

    public String peek(){
        notifyClipboardObservers();
        return texts.peek();
    }

    public boolean empty(){
        return texts.empty();
    }

    public void clear(){
        texts.clear();
        notifyClipboardObservers();
    }

    public void addClipboardObserver(ClipboardStackObserver observer){
        observers.add(observer);
    }

    public void removeClipboardObserver(ClipboardStackObserver observer){
        observers.remove(observer);
    }

    private void notifyClipboardObservers(){
        for (ClipboardStackObserver observer: observers ) {
            observer.updateClipboardStack();
        }
    }

}
