package com.example.javapractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;


class Txn{
    int id;
    int value;
    String type;

    public Txn(int id, int value, String type){
        this.id = id;
        this.value = value;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Txn [id=" + id + ", value=" + value + ", type=" + type + "]";
    }

    
    

}

public class Streams {




    
    public static void main(String[] args) {

        List<Txn> txns = new ArrayList<>();
        txns.add(new Txn(1,1,"GROCERY"));
        txns.add(new Txn(2,2,"EDUCATION"));
        txns.add(new Txn(3,3,"GROCERY"));
        txns.add(new Txn(4,4,"GROCERY"));
        txns.add(new Txn(5,5,"ENTERTAINMENY"));
        txns.add(new Txn(6,6,"EDUCATION"));
        txns.add(new Txn(6,6,"EDUCATION"));
        /*
         * allMatch()
         * anyMatch()
         * noneMatch()
         */
        boolean allExpensive = txns.stream().allMatch(t -> t.getValue() > 2); //to check that all the elements satisfy the condition
        System.out.println(allExpensive); //false
        boolean anyExpensive = txns.stream().anyMatch(t -> t.getValue() > 2); //to check that any of the elements satisfy the condition
        System.out.println(anyExpensive); //true
        boolean noneExpensive = txns.stream().noneMatch(t -> t.getValue() > 2); //to check that none of the elements satisfy the condition
        System.out.println(noneExpensive); //false

        /*
         * findAny()
         * findFirst() 
         * use after filter()
         */ 
        Optional<Txn> findAnyTxn = txns.stream().filter(t -> t.getType().equalsIgnoreCase("EDUCATION"))
                                        .findAny();
        System.out.println("findany -->" + findAnyTxn.toString());
        Optional<Txn> firstAnyTxn = txns.stream().filter(t -> t.getType().equalsIgnoreCase("EDUCATION"))
                                        .findFirst();
        System.out.println("findFirst -->" + firstAnyTxn.toString());

        /* l#91
         * Optional<T> is a container class to represent the existence or absence of a value.
         * this can be done by ifPresent() in a stream 
         * ifPresent() do not returns anything and is terminal operation
         */
        txns.stream().filter(t -> t.getType().equals("EDUCATION"))
                                        .findAny().ifPresent(System.out::println); //method referencing


        /*
         * Mapping. map() transforms each element and returns transformed element
         * intermittent method. need to collect
         */

        List<String> strs = Arrays.asList("Oracle", "Java", "Magazine");
        List<Integer> strsLength = strs.stream().map(String::length).collect(Collectors.toList());
        System.out.println(strsLength);

       
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8);
        List<Integer> twoevenSquares = numbers.stream()
                                        .filter(num->{ //filtering each element
                                            System.out.println("filtering --> " + num); 
                                            return num%2==0;   //returning on condition
                                        })
                                        .map(num-> {  //transforming elements that are return by filter
                                            System.out.println("mapping --> " + num);
                                            return num*num;   //return transformed element
                                        })
                                        .limit(2)  //limiting the result to size 2
                                        .collect(Collectors.toList()); // collecting final result.
        twoevenSquares.forEach(n -> System.out.println(n));

         /*
         * to get total sum use reduce()
         * (initial_value(int sum =0, before starting loop), (a,b) -> a+b )
         */
        int sum = numbers.stream().reduce(0, (a,b)-> a+b);
        System.out.println("total sum of array using reduce()--> " + sum);

        int totalProduct = numbers.stream().reduce(1, (a,b) -> a*b);
        System.out.println("total product of array using reduce() --> " + totalProduct);

        int max = numbers.stream().reduce(1, Integer::max);
        System.out.println("max of the array using reduce() and method reference --> " + max);

        /*
         * sum of even numbers
         */
        int sumEvenNums = numbers.stream().filter(a -> a%2==0).reduce(0, (a,b) -> a+b);
        System.out.println("even number sum --> " + sumEvenNums);

        //using sum()
        int statement =  txns.stream().mapToInt(Txn::getValue).sum();
        // .reduce(0, (t1, t2) -> t1 + t2);
        System.out.println("statement -> " + statement);

        List<String> g7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K", "Canada");
        String g7Combined = g7.stream().map(g -> g.toUpperCase()).collect(Collectors.joining(", "));
        System.out.println(g7Combined);


        //flatMap
        List<String> teamIndia = Arrays.asList("Virat", "Dhoni", "Jadeja"); 
        List<String> teamAustralia = Arrays.asList("Warner", "Watson", "Smith"); 
        List<String> teamEngland = Arrays.asList("Alex", "Bell", "Broad"); 
        List<String> teamNewZeland = Arrays.asList("Kane", "Nathan", "Vettori"); 
        List<String> teamSouthAfrica = Arrays.asList("AB", "Amla", "Faf"); 
        List<String> teamWestIndies = Arrays.asList("Sammy", "Gayle", "Narine"); 
        List<String> teamSriLanka = Arrays.asList("Mahela", "Sanga", "Dilshan"); 
        List<String> teamPakistan = Arrays.asList("Misbah", "Afridi", "Shehzad"); 
        List<List<String>> playersInWorldCup2016 = new ArrayList<>(); 
        playersInWorldCup2016.add(teamIndia); 
        playersInWorldCup2016.add(teamAustralia); 
        playersInWorldCup2016.add(teamEngland); 
        playersInWorldCup2016.add(teamNewZeland); 
        playersInWorldCup2016.add(teamSouthAfrica); 
        playersInWorldCup2016.add(teamWestIndies); 
        playersInWorldCup2016.add(teamSriLanka); 
        playersInWorldCup2016.add(teamPakistan);

        List<String> allPlayersUsingFlatMap = playersInWorldCup2016.stream().flatMap(pList -> pList.stream()).collect(Collectors.toList());
        System.out.println("after flat map -> " + allPlayersUsingFlatMap);
        

        //list to map
        //Function.identity() --> to pass the object itself
        Map<Integer,Txn> map = txns.stream().collect(Collectors.toMap(Txn::getId, Function.identity(), (e1, e2) -> e1));
        System.out.println("converted map from list of txns -> " + map);


        //sorting map
        Map<String,Integer> budget = new HashMap<>();
        budget.put("grocery", 150);
        budget.put("utility", 130);
        budget.put("miscellneous", 90);
        budget.put("rent", 1150);
        budget.put("clothes", 120);
        budget.put("transportation", 100);

        //sorting map on key asc
        Map<String, Integer> sortedBudgetAsc = budget.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2) -> e1, LinkedHashMap::new));
        System.out.println("map after sorting key ascending order --> " + sortedBudgetAsc);
        
        //sorting map on key desc
        Map<String, Integer> sortedBudgetDesc = budget.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2) -> e1, LinkedHashMap::new));
        System.out.println("map after sorting key Decreasing--> " + sortedBudgetDesc);


        //sorting all players list asc
        String sortedPlayersAsc = allPlayersUsingFlatMap.stream().sorted().collect(Collectors.joining(", "));
        System.out.println("sorted player asc --> " + sortedPlayersAsc);

        //sorting all players list desc
        String sortedPlayersDesc = allPlayersUsingFlatMap.stream().sorted(Collections.reverseOrder()).collect(Collectors.joining(", "));
        System.out.println("sorted player desc --> " + sortedPlayersDesc);

        //sorting all players list second character
        String sortedPlayers = allPlayersUsingFlatMap.stream().sorted((a,b) -> a.charAt(1) - b.charAt(1)).collect(Collectors.joining(", "));
        System.out.println("sorted player second character --> " + sortedPlayers);

        Consumer<Integer> consumer = t -> System.out.println("Printing : " + t*t);
        consumer.accept(10);
        List<Integer> list1 = Arrays.asList(1,2,3,4,5);
        // list1.stream().forEach(consumer);
        list1.stream().forEach(consumer);

        Predicate<Integer> predicate = (t)-> { return t%2==0;};
        list1.stream().filter(predicate).forEach(consumer);

        Supplier<String> supplier = () -> {return "hi Sumit";};
        System.out.println(supplier.get());
    }
}
