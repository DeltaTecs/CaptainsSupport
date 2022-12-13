package io;

import java.io.*;

/**
 * Handles writing and loading classes to disk. Useful for storing settings or other user saved data
 * @param <T> Class Type to be serialized
 */
public class Serializer <T> {

    private final String filename;

    private T subject = null; // intermediate representation

    public Serializer(String filename) {
        this.filename = filename;
    }

    /**
     * Loads the specified class from the disk and stores an intermediate
     * @return the loaded subject
     */
    public T load() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));

        T subject = (T) in.readObject();
        this.subject = subject;

        return subject;
    }

    public void save(T subject) throws IOException {
        if (subject == null)
            throw new NullPointerException("Subject must not be null");
        this.subject = subject; // store given into intermediate
        // attempt write
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
        out.writeObject(subject);
    }

    /**
     * returns the latest loaded subject
     * @return most recent class instance
     */
    public T getSubject() {
        return subject;
    }

}
