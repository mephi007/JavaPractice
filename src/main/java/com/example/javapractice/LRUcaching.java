package com.example.javapractice;

import java.util.HashMap;
import java.util.Map;

class Node{
    int key;
    int value;
    Node next;
    Node prev;
}

public class LRUcaching {
    Node head;
    Node tail;
    int capacity;
    Map<Integer, Node> map;

    public LRUcaching(int cap){
        this.capacity = cap;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }
    public int get(int key){
        Node cur = map.get(key);
        if(cur != null){
            Node prev = cur.prev;
            prev.next = cur.next;
            cur.prev = prev;
            Node tailPrev = tail.prev;
            tailPrev.next = cur;
            cur.prev = tailPrev;
            cur.next = tail;
            tail.prev = cur;
        }
        return cur.value;
    }



    public void add(int key, int value){
        Node newNode = new Node();
        newNode.key = key;
        newNode.value = value;
        Node prev = tail.prev;
        prev.next = newNode;
        newNode.prev = prev;
        tail.prev = newNode;
        newNode.next = tail;
        map.put(key, newNode);
    }

    public static void main(String[] args) {
        LRUcaching lru = new LRUcaching(3);
        lru.add(1,1);
        lru.add(2,2);
        lru.add(3,3);

        System.out.println(lru.get(1));

        lru.add(4,4);
        System.out.println(lru.get(2));
    }

}
