package org.kimbs.demo.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Machine<T, E> {
    int getPrice(E name);
    CompletableFuture<T> getItem(E name);
    CompletableFuture<List<T>> getItems(E... names);
}
