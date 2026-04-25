/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package objects;

/**
 *
 * @author LENOVO
 */
import java.util.ArrayList;
import java.util.List;

public class GenericDAO<T> implements BaseDAO<T> {
    private final String collectionName;
    private final Class<T> clazz; 
    private List<T> dataList = new ArrayList<>();

    public GenericDAO(String collectionName, Class<T> clazz) {
        this.collectionName = collectionName;
        this.clazz = clazz;
    }

    @Override
    public void save(T entity) {
        dataList.add(entity);
        System.out.println("Menyimpan ke " + collectionName + ": " + clazz.getSimpleName());
    }

    @Override
    public void update(int index, T entity) { dataList.set(index, entity); }

    @Override
    public void delete(int index) { dataList.remove(index); }

    @Override
    public List<T> findAll() { return dataList; }

    @Override
    public T findByIndex(int index) { return dataList.get(index); }
}
