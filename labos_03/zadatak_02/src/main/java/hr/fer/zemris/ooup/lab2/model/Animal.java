package hr.fer.zemris.ooup.lab2.model;

public abstract class Animal {

    public abstract String name();
    public abstract String greet();
    public abstract String menu();


    public void animalPrintGreeting() {
        System.out.printf("%s pozdravlja: %s\n",name(),greet());
    }

    public void animalPrintMenu() {
        System.out.printf("%s voli %s.\n",name(),menu());
    }



}
