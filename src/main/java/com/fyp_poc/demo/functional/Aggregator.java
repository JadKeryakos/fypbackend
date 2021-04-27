package com.fyp_poc.demo.functional;

@FunctionalInterface
public interface Aggregator<X extends Number> {
    X process(X arg1, X arg2);
}
