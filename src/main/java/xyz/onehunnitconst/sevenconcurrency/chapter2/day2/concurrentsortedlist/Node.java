package xyz.onehunnitconst.sevenconcurrency.chapter2.day2.concurrentsortedlist;

import java.util.concurrent.locks.ReentrantLock;

public class Node {
    int value;
    Node prev;
    Node next;
    ReentrantLock lock = new ReentrantLock();

    Node () {}

    Node (int value, Node prev, Node next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }
}
