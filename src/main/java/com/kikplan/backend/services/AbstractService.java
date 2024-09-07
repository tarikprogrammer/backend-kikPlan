package com.kikplan.backend.services;

import java.util.List;

public interface AbstractService<T>{
    T save(T dto);
    T findById(Long id);
    List<T> findAll();
    void delete(Long id);
}
