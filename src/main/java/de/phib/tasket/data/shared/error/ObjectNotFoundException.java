package de.phib.tasket.data.shared.error;

/**
 * Thrown, if no object can be found in a resository for a given id.
 */
public class ObjectNotFoundException extends RuntimeException {

    /**
     * Creates a new instance of ObjectNotFoundException.
     *
     * @param id         the id of the object that was not found
     * @param objectType the type of the object that was not found
     */
    public ObjectNotFoundException(String id, Class objectType) {
        super("Could not find object of type '" + objectType.getSimpleName() + "' with id '" + id + "'");

    }
}
