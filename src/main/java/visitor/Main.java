package visitor;

import composite.*;
import factory.DataFrame;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        FileCOMP f1 = new FileCOMP("src/main/resources/dir1/DimenLookupAge8277.csv");
        FileCOMP f2 = new FileCOMP("src/main/resources/dir2/subdir2/cities.json");
        FileCOMP f3 = new FileCOMP("src/main/resources/dir1/example.txt");

        DirectoryCOMP dir1 = new DirectoryCOMP("src/main/resources/dir1");
        DirectoryCOMP dir2 = new DirectoryCOMP("src/main/resources/dir2");

        List<DataFrame> list = Arrays.asList(dir1, f1, f3);
        List<DataFrame> list2 = Arrays.asList(dir2, f2);

        Visitor v;

        System.out.println("Maxim dir1 SortOrder");
        for (DataFrame d : list) {
            v = new MaximumVisitor("SortOrder");
            d.accept(v);
            System.out.println(v.getResult());
        }

        System.out.println("Maxim dir2 LatD");
        for (DataFrame d : list2) {
            v = new MaximumVisitor("LatD");
            d.accept(v);
            System.out.println(v.getResult());
        }

        System.out.println("\nMinim dir1 SortOrder");
        for (DataFrame d : list) {
            v = new MinimumVisitor("SortOrder");
            d.accept(v);
            System.out.println(v.getResult());
        }

        System.out.println("Minim dir2 LatD");
        for (DataFrame d : list2) {
            v = new MinimumVisitor("LatD");
            d.accept(v);
            System.out.println(v.getResult());
        }

        System.out.println("\nSuma dir1 SortOrder");
        for (DataFrame d : list) {
            v = new SumVisitor("SortOrder");
            d.accept(v);
            System.out.println(v.getResult());
        }

        System.out.println("Suma dir2 LatD");
        for (DataFrame d : list2) {
            v = new SumVisitor("LatD");
            d.accept(v);
            System.out.println(v.getResult());
        }

        System.out.println("\nMitjana dir1 SortOrder");
        for (DataFrame d : list) {
            v = new AverageVisitor("SortOrder");
            d.accept(v);
            System.out.println(v.getResult());
        }

        System.out.println("Mitjana dir2 LatD");
        for (DataFrame d : list2) {
            v = new AverageVisitor("LatD");
            d.accept(v);
            System.out.println(v.getResult());
        }
    }

}