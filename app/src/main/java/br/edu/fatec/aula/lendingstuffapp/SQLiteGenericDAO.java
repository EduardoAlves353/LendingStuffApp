package br.edu.fatec.aula.lendingstuffapp;

import java.util.List;

public interface SQLiteGenericDAO<T> {

    public long create(T t) throws Exception;
    public T readById(long id) throws Exception;
    public List<T> readAll() throws Exception;
    public void update(T t) throws Exception;
    public void delete(T t) throws Exception;

}
