package com.example.accounting.adapter.rest.dto;

import lombok.ToString;

@ToString
public class Tuple<U,V> {
    private final U u;
    private final V v;

    public Tuple(U u, V v) {
        this.u = u;
        this.v = v;
    }
}
