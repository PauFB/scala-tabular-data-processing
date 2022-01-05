package composite;

import factory.*;
import visitor.*;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;

public class DirectoryCOMP implements DataFrame {

    private final String name;
    private final List<DataFrame> children;

    public DirectoryCOMP(String directoryPath) {
        name = directoryPath;
        children = new LinkedList<>();
        try {
            File directory = new File(directoryPath);
            for (File file : directory.listFiles()) {
                if (!file.isDirectory()) {
                    children.add(new FileCOMP(file.getAbsolutePath()));
                    continue;
                }
                children.add(new DirectoryCOMP(file.getAbsolutePath()));
            }
        } catch (Exception e){
            System.out.println("El directori esta buit");
        }

    }

    public List<DataFrame> getChildren(){
        return children;
    }

    public String getName(){
        return name;
    }

    public String at(int id, String label) {
        return null;
    }

    public String iat(int i, int j) {
        return null;
    }

    public int columns() {
        return this.getLabelList().size();
    }

    public LinkedList<String> getLabelList() {
        LinkedList<String> labelList = new LinkedList<>();
        LinkedList<String> newlabelList = new LinkedList<>();
        for (DataFrame child : this.children) {
            newlabelList = child.getLabelList();
            for (String s : newlabelList){
                if (!labelList.contains(s)){
                    labelList.add(s);
                }
            }
        }
        return labelList;
    }

    public int size() {
        int result = 0;
        for (DataFrame child : this.children)
            result += child.size();
        return result;
    }

    public ArrayList<String> sort(String label, Comparator<Object> c) {
        ArrayList<String> temp = (ArrayList<String>) getColumn(label).clone();
        temp.sort(c);
        return temp;
    }

    public Data query(String label, Predicate<String> predicate) {
        Data result = null;
        boolean firstHasBeenAdded = false;
        for (DataFrame child : children) {
            if (!firstHasBeenAdded) {
                if (child.query(label, predicate) != null){
                    result = child.query(label, predicate);
                    firstHasBeenAdded = true;
                }
            } else if (child.query(label, predicate) != null) {
                for (int i = 0; i < result.getContent().size(); i++) {
                    result.getContent().get(i).addAll(child.query(label, predicate).getContent().get(i));
                }
            }
        }
        return result;
    }

    public Double max(String label) {
        double maxValue = Double.MIN_VALUE;
        for (DataFrame child : children){
            if (child.max(label) != null){
                maxValue = Math.max(child.max(label), maxValue);
            }
        }
        return maxValue;
    }

    public Double min(String label) {
        double minValue = Double.MAX_VALUE;
        for (DataFrame child : children) {
            if (child.min(label) != null) {
                minValue = Math.min(child.min(label), minValue);
            }
        }
        return minValue;
    }

    public Double average(String label) {
        ArrayList<String> list;
        double accumulator = 0.0;
        int nElements = 0;
        for (DataFrame child : children) {
            list = child.getColumn(label);
            if (list != null) {
                for (String value : list) {
                    accumulator += Integer.parseInt(value);
                    nElements++;
                }
            }
        }
        if (nElements != 0){
            return accumulator/nElements;
        }
        return null;
    }

    public Double sum(String label) {
        Double sum = 0.0;
        for (DataFrame child : children) {
            if (child.sum(label) != null){
                sum += child.sum(label);
            }
        }
        return sum;
    }

    public LinkedList<ArrayList<String>> getContent() {
        LinkedList<ArrayList<String>> aux = new LinkedList<>();
        for (DataFrame child : this.children) {
            aux.addAll(child.getContent());
        }
        return aux;
    }

    public ArrayList<String> getColumn(String label) {
        ArrayList<String> column = new ArrayList<>();
        for (DataFrame child : children){
            column.addAll(child.getColumn(label));
        }
        return column;
    }

    public void accept(Visitor v, String label) {
        v.visit(this, label);
    }

    public Iterator<ArrayList<String>> iterator() {
        return children.get(0).iterator();
    }

    @Override
    public String toString() {
        StringBuilder aux = new StringBuilder();
        for (DataFrame child : children){
            aux.append(child.toString()).append("\n");
        }
        return aux.toString();
    }
}
