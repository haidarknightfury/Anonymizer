package com.smartfox.anonymizer.batch.common;

import java.util.List;

/**
 * Base DAO Operations
 * @author hdargaye
 *
 * @param <E>
 */
public interface BaseDAOOperation<E> {

    List<E> findAll(int limit);

    List<E> findAll(int limit, int offset);

    void insertAll(List<E> elements);
}
