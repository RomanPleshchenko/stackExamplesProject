package com.example.stackexamplesproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@RestController
public class ThreadExecutorManagerController {

    @GetMapping("addCachedThreads")
    public String addCachedThreads(@RequestParam int countThreads, boolean isDaemon) {
        ThreadFactory threadFactory = r -> {
            Thread t = new Thread(r);
            t.setDaemon(isDaemon);
            return t;
        };

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool(threadFactory);
        while (countThreads-- > 0) {
            cachedThreadPool.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        return "" + countThreads + "  threads were added";
    }

    @GetMapping("addThreads")
    public String addThreads(@RequestParam int countThreads, boolean isDaemon) {
        ThreadFactory threadFactory = r -> {
            Thread t = new Thread(r);
            t.setDaemon(isDaemon);
            return t;
        };

        ExecutorService cachedThreadPool = Executors.newFixedThreadPool(10, threadFactory);
        while (countThreads-- > 0) {
            cachedThreadPool.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        return "" + countThreads + "  threads were added";
    }
}
