package com.softwarehouse.salescontrol.comom;

import java.util.List;

public interface ServiceProcessor<T> {
    public List<T> getAll();
    public T getById(Long id);
    public T save(T entity);
    public T update(T entity);
    public void deleteById(Long id);
}
