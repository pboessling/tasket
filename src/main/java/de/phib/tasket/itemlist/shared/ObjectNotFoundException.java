package de.phib.tasket.itemlist.shared;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String id, Class objectType) {
        super("Could not find object of type '" + objectType.getSimpleName() + "' with id '" + id + "'");

    }
}
