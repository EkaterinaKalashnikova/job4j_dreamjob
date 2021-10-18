package ru.job4j.concurrent;

public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread another = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread third = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        another.start();
        second.start();
        third.start();
        System.out.println(Thread.currentThread().getName());
    }
}
