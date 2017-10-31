package com.smith.ufc.data.base;

import java.util.List;

/**
 * Created by Charlton on 10/31/17.
 */

public interface Repository<T, S> {
    void add(T item);

    void add(List<T> items);

    void update(T item);

    void remove(T item);

    List<T> query(S specification);
}