package de.phib.tasket.data.shared.error;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String id, Class objectType) {
        super("Could not find object of type '" + objectType.getSimpleName() + "' with id '" + id + "'");

    }
}
