package mapreduce;

import composite.*;
import java.util.*;
import factory.DataFrame;


public class Main {
    public static void main(String[] args) {

        FileCOMP f1 = new FileCOMP("DimenLookupAge8277.csv");
        FileCOMP f2 = new FileCOMP("cities.json");
        FileCOMP f3 = new FileCOMP("example.txt");

        DirectoryCOMP dir1 = new DirectoryCOMP("dir1");
        DirectoryCOMP dir2 = new DirectoryCOMP("dir2");

        List<DataFrame> list = Arrays.asList(dir1, dir2, f1, f2, f3);

        List<Integer> sizes  = MapReduce.map(list, new Size());
        System.out.println("List of sizes:");
        for (Integer elem : sizes)
            System.out.println(elem);
        System.out.println("Sizes Average: " + MapReduce.reduce(sizes));

        List<Integer> columns  = MapReduce.map(list, new Columns());
        System.out.println("\nList of columns:");
        for (Integer elem : columns)
            System.out.println(elem);
        System.out.println("Columns Average: " + MapReduce.reduce(columns));

        List<DataFrame> query_list = MapReduce.map(list, new Query("Code", x -> Integer.parseInt(x) > 888));
        System.out.println("\nList of querys with Code > 888:");
        for (DataFrame elem : query_list)
            System.out.println(elem);
        System.out.println("Result of Code > 888:\n" + MapReduce.reduce(query_list));

    }
}
