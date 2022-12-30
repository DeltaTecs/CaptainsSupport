package io.serializeables;

import java.lang.reflect.Field;

/**
 * Change applied to a Field in Settings for example
 */
public class Change {

    private String name;
    private Field field;
    private Object original;
    private Object update;


    public Change(String name, Field field, Object original, Object update) {
        this.name = name;
        this.field = field;
        this.original = original;
        this.update = update;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Object getOriginal() {
        return original;
    }

    public void setOriginal(Object original) {
        this.original = original;
    }

    public Object getUpdate() {
        return update;
    }

    public void setUpdate(Object update) {
        this.update = update;
    }
}
