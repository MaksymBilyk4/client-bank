package com.clientbank.dev.services;

import java.util.List;

public interface I_Service<T> {
    List<T> findAll();

    T getOne(Long id);

    T save(T obj);

    void saveAll(List<T> entities);

    void deleteAll(List<T> entities);

    boolean delete(T obj);

    boolean deleteById(Long id);

    boolean update(T obj);
}
