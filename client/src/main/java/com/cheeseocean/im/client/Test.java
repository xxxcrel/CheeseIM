package com.cheeseocean.im.client;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

/**
 * @author xxxcrel
 * @date 2024/5/16 21:35
 */
public class Test {

    private volatile int i = 0;

    void increment() {
        i++;
    }

    public static void main(String[] args) {
        Test t = new Test();
        List<Runnable> tasks = IntStream.of(10).mapToObj(i -> (Runnable) t::increment).toList();
        tasks.forEach(CompletableFuture::runAsync);
    }
}
