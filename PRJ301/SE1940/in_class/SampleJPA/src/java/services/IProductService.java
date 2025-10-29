package services;

import java.util.List;

public interface IProductService<T> {
    void close();
    boolean add(T prodcut);
    T searchById(int id);
    List<T> getAll();
    boolean update(T product);
    boolean delete(T product);
}
