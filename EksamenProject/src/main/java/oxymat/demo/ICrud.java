package oxymat.demo;

import java.util.ArrayList;

/**
 * A interface for CRUD operations in a repository
 * @Author ALS
 * @param <T> the class the repository contains.
 */
public interface ICrud<T> {
    void create(T t);
    T read(int id);
    ArrayList<T> readAll();
    void update(T t);
    void delete(int id);
}