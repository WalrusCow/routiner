package ca.wmcd.routiner;

/**
 * Basic callback interface.
 */
public interface Callback<T> {
    void call(T data);
}
