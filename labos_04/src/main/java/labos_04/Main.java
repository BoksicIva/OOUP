package labos_04;

import labos_04.grafical_object.GraphicalObject;
import labos_04.grafical_object.Oval;
import labos_04.grafical_object.LineSegment;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<GraphicalObject> objects = new ArrayList<>();

//        objects.add(new LineSegment(new Point(50,50),new Point(50,100)));
//        objects.add(new Oval(new Point(200,50),new Point(150,100)));
        objects.add(new LineSegment());
        objects.add(new Oval());

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new GUI(objects).setVisible(true);
            }
        });
    }
}
