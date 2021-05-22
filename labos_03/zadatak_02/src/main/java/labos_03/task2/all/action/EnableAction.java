package labos_03.task2.all.action;

import javax.swing.*;

public class EnableAction {
    private JButton button;
    private AbstractAction action;

    public EnableAction(JButton button, AbstractAction action) {
        this.button = button;
        this.action = action;
    }


    public void setEnable(boolean empty){
        if(empty){
            action.setEnabled(false);
            button.setEnabled(false);
        }else{
            button.setEnabled(true);
            action.setEnabled(true);
        }
    }
}
