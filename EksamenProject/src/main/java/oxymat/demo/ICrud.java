package oxymat.demo;

import java.util.ArrayList;

public interface ICrud<T> {
    void create(T t);
    void read(int id);
    ArrayList<T> readAll();
    void update(T t);
    void delete(int id);


}
