package com.niken.perkembangananak.base;

/**
 * Created by Firman on 4/17/2019.
 */
public interface OnExecuteListener<E> {

    void onExecuted(E e);
    void onError(Throwable throwable);
}
