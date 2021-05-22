package labos_03.task2.all.plugin;


import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class PluginFactory {
    private static final String path = "labos_03.task2.all.plugin.implementation.";

    public static Plugin getIstance(String pluginName) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {

        Class<Plugin> clazz = null;
        clazz = (Class<Plugin>) Class.forName(path + pluginName);

        Constructor<?> ctr = clazz.getConstructor();
        Plugin plugin = (Plugin) ctr.newInstance();

        return plugin;

    }


}
