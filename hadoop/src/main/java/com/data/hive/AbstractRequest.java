package com.data.hive;

import java.util.concurrent.Callable;

/**
 * @author whp 19-7-18
 */
public abstract class AbstractRequest<T> {
    protected abstract Callable<T> callable();
    public T now() {
        try {
            return callable().call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
