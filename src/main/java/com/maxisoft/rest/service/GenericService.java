package com.maxisoft.rest.service;

import java.util.List;

public interface GenericService<T, ID> {
    T getById(ID id);

    boolean delete(ID id);

    T update(T t);

    List<T> getAll();

    T save (T t);
}
