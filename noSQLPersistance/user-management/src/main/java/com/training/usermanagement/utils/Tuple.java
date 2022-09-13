package com.example.usermanagement.utils;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Tuple<T, U> {
    private final T _1;
    private final U _2;

    public Tuple(T t, U u) {
        this._1 = t;
        this._2 = u;
    }

}
