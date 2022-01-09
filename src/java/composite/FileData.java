package composite;

import factory.*;
import visitor.*;

import java.util.*;
import java.util.function.Predicate;

public class FileData implements DataFrame {

    private final Data data;
    private final String name;

    public FileData(String filePath) {
        name = filePath;
        DataFrame dataFrame = null;             //Create a new Dataframe depending on its extension
        if (filePath.contains(".csv")) {
            dataFrame = new CSVData(filePath);
        } else if(filePath.contains(".json")){
            dataFrame = new JSONData(filePath);
        } else if(filePath.contains(".txt")){
            dataFrame = new TXTData(filePath);
        }
        if (dataFrame != null){
            data = new Data(dataFrame.getLabelList(),dataFrame.getContent());   //Instantiate data with the information of the dataframe
        } else {
            data = null;
            System.out.println("File is not a Dataframe");
        }
    }

    public Data getData() {
        return data;
    }

    public String getName() {
        return name;
    }

    public String at(int id, String label) {
        return data.at(id, label);
    }

    public String iat(int i, int j) {
        return data.iat(i,j);
    }

    public int columns() {
        return data.columns();
    }

    public int size() {
        return data.size();
    }

    public ArrayList<String> sort(String label, Comparator<String> c) {
        return data.sort(label, c);
    }

    public Data query(String label, Predicate<String> predicate) {
        return data.query(label, predicate);
    }

    public Double max(String label) {
        return data.max(label);
    }

    public Double min(String label) {
        return data.min(label);
    }

    public Double average(String label) {
        return data.average(label);
    }

    public Double sum(String label) {
        return data.sum(label);
    }

    public LinkedList<ArrayList<String>> getContent(){
        return data.getContent();
    }

    public LinkedList<String> getLabelList() {
        return data.getLabelList();
    }

    public ArrayList<String> getColumn(String label) {
        return data.getColumn(label);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Iterator<ArrayList<String>> iterator() {
        return data.iterator();
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
