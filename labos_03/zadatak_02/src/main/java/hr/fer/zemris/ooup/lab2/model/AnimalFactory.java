package hr.fer.zemris.ooup.lab2.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AnimalFactory {


    public static Animal newInstance(String animalKind, String name) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Class<Animal> clazz = null;
        clazz = (Class<Animal>)Class.forName("hr.fer.zemris.ooup.lab2.model.plugins."+animalKind);

        Constructor<?> ctr = clazz.getConstructor(String.class);
        Animal animal = (Animal)ctr.newInstance(name);

        return animal;

    }
}
