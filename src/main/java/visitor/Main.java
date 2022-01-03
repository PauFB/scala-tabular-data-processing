package visitor;

import composite.*;
import factory.DataFrame;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        FileCOMP f1 = new FileCOMP("DimenLookupAge8277.csv");
        FileCOMP f2 = new FileCOMP("cities.json");
        FileCOMP f3 = new FileCOMP("example.txt");

        DirectoryCOMP dir1 = new DirectoryCOMP("dir1");
        DirectoryCOMP dir2 = new DirectoryCOMP("dir2");

        List<DataFrame> list = Arrays.asList(dir1, f1, f3);
        List<DataFrame> list2 = Arrays.asList(dir2, f2);

        Visitor v;

        System.out.println("Maxim dir1 SortOrder");
        for (DataFrame d : list) {
            v = new MaximumVisitor();
            d.accept(v,"SortOrder");
            System.out.println(v.getResult());
        }
        System.out.println("Maxim dir2 LatD");
        for (DataFrame d : list2) {
            v = new MaximumVisitor();
            d.accept(v,"LatD");
            System.out.println(v.getResult());
        }

        System.out.println("\nMinim dir1 SortOrder");
        for (DataFrame d : list) {
            v = new MinimumVisitor();
            d.accept(v,"SortOrder");
            System.out.println(v.getResult());
        }

        System.out.println("Minim dir2 LatD");
        for (DataFrame d : list2) {
            v = new MinimumVisitor();
            d.accept(v,"LatD");
            System.out.println(v.getResult());
        }

        System.out.println("\nSuma dir1 SortOrder");
        for (DataFrame d : list) {
            v = new SumVisitor();
            d.accept(v,"SortOrder");
            System.out.println(v.getResult());
        }

        System.out.println("Suma dir2 LatD");
        for (DataFrame d : list2) {
            v = new SumVisitor();
            d.accept(v,"LatD");
            System.out.println(v.getResult());
        }

        System.out.println("\nMitjana dir1 SortOrder");
        for (DataFrame d : list) {
            v = new AverageVisitor();
            d.accept(v,"SortOrder");
            System.out.println(v.getResult());
        }

        System.out.println("Mitjana dir2 LatD");
        for (DataFrame d : list2) {
            v = new AverageVisitor();
            d.accept(v,"LatD");
            System.out.println(v.getResult());
        }
    }

}