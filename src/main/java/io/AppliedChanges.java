package io;

import io.serializeables.Change;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Set of Changes applied to Settings for example
 */
public class AppliedChanges<T> {

    /**
     * Original object with value
     */
    private T original;

    /**
     * Update Object with changes
     */
    private T update;

    private List<Change> changes = new ArrayList<Change>();

    public AppliedChanges(T original, T update) {
        this.original = original;
        this.update = update;


    }

    public void detectChanges() throws IllegalAccessException {
        for (Field field : original.getClass().getFields()) {
            if (!field.get(update).equals(field.get(original)) && (field.getType().equals(int.class) || field.getType().equals(String.class) || field.getType().equals(boolean.class) || field.getType().equals(float.class) || field.getType().equals(double.class))) {
                // change took place
                changes.add(new Change("", field, field.get(original), field.get(update)));
            }
        }
    }

    public void addChange(Change change) {
        changes.add(change);
    }

    public T getOriginal() {
        return original;
    }

    public T getUpdate() {
        return update;
    }

    public List<Change> getChanges() {
        return changes;
    }
}
