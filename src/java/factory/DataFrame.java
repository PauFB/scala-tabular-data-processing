package factory;

import visitor.Visitor;

import java.util.*;
import java.util.function.Predicate;

public interface DataFrame extends Iterable<ArrayList<String>> {

    String at(int id, String label);

    String iat(int i, int j);

    int columns();

    int size();

    ArrayList<String> sort(String label, Comparator<String> c);

    Data query(String label, Predicate<String> predicate);

    LinkedList<ArrayList<String>> getContent();

    LinkedList<String> getLabelList();

    ArrayList<String> getColumn(String label);

    void accept(Visitor v);

}
