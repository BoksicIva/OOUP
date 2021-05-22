package hr.fer.zemris.ooup.lab2.model;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String... args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, NoSuchMethodException, IllegalAccessException {

        Animal parrot=AnimalFactory.newInstance("Parrot","Modrobradi");

        parrot.animalPrintGreeting();
        parrot.animalPrintMenu();

        Animal tiger= AnimalFactory.newInstance("Tiger","Snjeznobradi");

        tiger.animalPrintGreeting();
        tiger.animalPrintMenu();
    }
}
