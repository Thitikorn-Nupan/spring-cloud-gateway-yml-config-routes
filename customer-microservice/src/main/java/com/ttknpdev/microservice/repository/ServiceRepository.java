package com.ttknpdev.microservice.repository;

import java.util.List;

public interface ServiceRepository<T> {
    List<T> findAll();
    T findById(Long pk);
    Boolean save(T obj);
    Boolean edit(T obj,Long pk);
    Boolean remove(Long pk);
}
