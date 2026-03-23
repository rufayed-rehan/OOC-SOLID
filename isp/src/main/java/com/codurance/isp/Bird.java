package com.codurance.isp;

public class Bird implements Flyable, Runnable {
    public void run() {
        System.out.print("Bird is running");
    }
    public void fly() {
        System.out.print("Bird is flying");
    }
}