package com.example.javapractice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static int m1(){
        try{
            System.out.println("executing try");
            return 1;
        }
        catch(Exception e){
            System.out.println("executing catch");
            return 2;
        }
        finally{
            System.out.println("executing final");
            return 3;
        }
    }
    

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println(m1());

        String s1 = new String("sumit");
        String s2 = "sumit";
        String s3 = "sumit";
        System.out.println(s1.intern());
        System.out.println(s1.equals(s2));
        System.out.println(s1 == s2);
        System.out.println(s2.equals(s3));
        System.out.println(s3 == s2);

        List<String> list = new CopyOnWriteArrayList<>();
        list.add("2");
        list.add("3");
        list.add("5");
        Iterator<String> itr = list.iterator();
        while(itr.hasNext()){
            String i = itr.next();
            System.out.println(i);
            list.remove(i);
            list.add("23");
        }
        while(itr.hasNext()){
            String i = itr.next();
            System.out.println(i);
            // list.remove(i);
            // list.add("23");
        }

        Map<Integer,String> map = new HashMap<>();
        map.put(1,"one");
        map.put(2,"two");
        Iterator<Integer> it = map.keySet().iterator();
        while(it.hasNext()){
            System.out.println(it.next() + "  --> " + map.get(it.next()));
            map.put(3,"three");
        }



    }
}
