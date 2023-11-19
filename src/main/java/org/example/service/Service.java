package org.example.service;

public interface Service<T> {

    T add(T t);

    T update(T t);

    T findById(Long id);

    boolean remove(Long id);
}
