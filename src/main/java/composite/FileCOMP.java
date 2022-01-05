package composite;

import factory.*;
import visitor.*;

import java.util.*;
import java.util.function.Predicate;

public class FileCOMP implements DataFrame {

    Data data;

    public FileCOMP(String filePath) {
        try {
            DataFrame dataFrame = null;
            if (filePath.contains(".csv")) {
                dataFrame = new CSVData(filePath);
            } else if(filePath.contains(".json")){
                dataFrame = new JSONData(filePath);
            } else if(filePath.contains(".txt")){
                dataFrame = new TXTData(filePath);
            }
            data = new Data(dataFrame.getLabelList(),dataFrame.getContent());
        } catch (Exception e){
            data = null;
            System.out.println("El fitxer no es un Dataframe");
        }

    }

    public Data getData() {
        return data;
    }

    public LinkedList<String> getLabelList() {
        return this.data.getLabelList();
    }

    public String at(int id, String label) {
        return this.data.at(id, label);
    }

    public String iat(int i, int j) {
        return this.data.iat(i,j);
    }

    public int columns() {
        return this.data.columns();
    }

    public int size() {
        return this.data.size();
    }

    public ArrayList<String> sort(String label, Comparator<Object> c) {
        return this.data.sort(label, c);
    }

    public Data query(String label, Predicate<String> predicate) {
        return this.data.query(label, predicate);
    }

    public Double max(String label) {
        return this.data.max(label);
    }

    public Double min(String label) {
        return this.data.min(label);
    }

    public Double average(String label) {
        return this.data.average(label);
    }

    public Double sum(String label) {
        return this.data.sum(label);
    }

    public LinkedList<ArrayList<String>> getContent(){
        return this.data.getContent();
    }

    public ArrayList<String> getColumn(String label) {
        return this.data.getColumn(label);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Iterator<ArrayList<String>> iterator() {
        return this.data.iterator();
    }

    public String toString() {
        return this.data.toString();
    }
}
