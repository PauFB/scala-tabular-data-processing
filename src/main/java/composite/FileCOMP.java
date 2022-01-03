package composite;

import factory.*;
import visitor.*;

import java.util.*;
import java.util.function.Predicate;

public class FileCOMP implements DataFrame {

    DataFrame dataFrame;

    public FileCOMP(String filePath) {
        if (filePath.contains(".csv")) {
            dataFrame = new CSVData(filePath);
        } else if(filePath.contains(".json")){
            dataFrame = new JSONData(filePath);
        } else if(filePath.contains(".txt")){
            dataFrame = new TXTData(filePath);
        }
    }

    public DataFrame getDataFrame() {
        return dataFrame;
    }


    public String at(int id, String label) {
        return dataFrame.at(id, label);
    }

    public String iat(int i, int j) {
        return dataFrame.iat(i,j);
    }

    public int columns() {
        return dataFrame.columns();
    }

    public int size() {
        return dataFrame.size();
    }

    public ArrayList<String> sort(String label, Comparator<Object> c) {
        return dataFrame.sort(label, c);
    }

    public DataFrame query(String label, Predicate<String> predicate) {
        return dataFrame.query(label, predicate);
    }

    public Double max(String label) {
        return dataFrame.max(label);
    }

    public Double min(String label) {
        return dataFrame.min(label);
    }

    public Double average(String label) {
        return dataFrame.average(label);
    }

    public Double sum(String label) {
        return dataFrame.sum(label);
    }

    public LinkedList<ArrayList<String>> getContent(){
        return dataFrame.getContent();
    }

    public ArrayList<String> getColumn(String label) {
        return dataFrame.getColumn(label);
    }

    public void accept(Visitor v, String label) {
        v.visit(this, label);
    }

    public Iterator<ArrayList<String>> iterator() {
        return dataFrame.iterator();
    }

    public String toString() {
        return dataFrame.toString();
    }
}
