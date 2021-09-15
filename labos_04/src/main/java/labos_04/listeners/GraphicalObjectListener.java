package labos_04.listeners;

import labos_04.grafical_object.GraphicalObject;

public interface GraphicalObjectListener {

    /**
     * // Poziva se kad se nad objektom promjeni bio što...
     * @param go
     */
    void graphicalObjectChanged(GraphicalObject go);

    /**
     * Poziva se isključivo ako je nad objektom promjenjen status selektiranosti
     * (baš objekta, ne njegovih hot-point-a).
     * @param go
     */
    void graphicalObjectSelectionChanged(GraphicalObject go);
}
