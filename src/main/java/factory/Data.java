package factory;

import java.util.*;
import java.util.function.Predicate;

public class Data {

    LinkedList<String> labelList;
    LinkedList<ArrayList<String>> content;

    public Data() {
        this.labelList = new LinkedList<>();
        this.content = new LinkedList<>();
    }

    public Data(LinkedList<String> labelList, LinkedList<ArrayList<String>> content) {
        this.labelList = labelList;
        this.content = content;
    }

    public String at(int id, String label) {
        int labelIndex = labelList.indexOf(label);

        if (labelIndex != -1 && id < size()){
            return content.get(labelIndex).get(id);
        }
        return null;
    }

    public LinkedList<String> getLabelList() {
        return this.labelList;
    }

    public String iat(int i, int j) {
        if (i < columns() && j< size()){
            return this.content.get(j).get(i);
        }
        return null;
    }

    public int columns() {
        return this.labelList.size();
    }

    public int size() {
        return this.content.get(0).size();
    }

    public ArrayList<String> sort(String label, Comparator<Object> c) {
        int labelIndex = labelList.indexOf(label);

        if (labelIndex != -1){
            ArrayList<String> temp = new ArrayList<>(content.get(labelIndex));
            temp.sort(c);
            return temp;
        }
        return null;
    }

    public Data query(String label, Predicate<String> func) {
        int col = labelList.indexOf(label);

        if (col != -1) {
            List<String> filtered_column = content.get(col).stream().filter(func).toList();

            LinkedList<ArrayList<String>> aux = new LinkedList<>();
            for (int k = 0; k < this.columns(); k++){
                aux.add(new ArrayList<>());
            }

            for (int j = 0; j < this.size(); j++) {
                if (filtered_column.contains(content.get(col).get(j))){
                    for (int i = 0; i < this.columns(); i++) {
                        aux.get(i).add(content.get(i).get(j));
                    }
                }
            }

            return new Data(labelList, aux);
        }
        return null;
    }

    public Double max(String label) {
        double max = Integer.MIN_VALUE;
        int labelIndex = labelList.indexOf(label);

        if (labelIndex != -1){
            try {
                for (String elem : content.get(labelIndex)){
                    max = Math.max(Integer.parseInt(elem), max);
                }
                return max;
            } catch (NumberFormatException e) {
                System.out.println(e);
                return null;
            }
        }
        return null;
    }

    public Double min(String label) {
        double min = Integer.MAX_VALUE;
        int labelIndex = labelList.indexOf(label);

        if (labelIndex != -1){
            try{
                for (String elem : content.get(labelIndex)){
                    min = Math.min(Integer.parseInt(elem), min);
                }
                return min;
            } catch (NumberFormatException e){
                System.out.println(e);
                return null;
            }
        }
        return null;
    }

    public Double average(String label) {
        double avg = 0;
        int labelIndex = labelList.indexOf(label);

        if (labelIndex != -1){
            try{
                for (String elem : content.get(labelIndex)){
                    avg += Integer.parseInt(elem);
                }
                return avg/content.get(labelIndex).size();
            } catch (NumberFormatException e){
                System.out.println(e);
                return null;
            }
        }
        return null;
    }

    public Double sum(String label) {
        double sum = 0;
        int labelIndex = labelList.indexOf(label);

        if (labelIndex != -1){
            try{
                for (String elem : content.get(labelIndex)){
                    sum += Integer.parseInt(elem);
                }
                return sum;
            } catch (NumberFormatException e){
                System.out.println(e);
                return null;
            }
        }
        return null;
    }

    public LinkedList<ArrayList<String>> getContent() {
        return content;
    }

    public ArrayList<String> getColumn(String label) {
        int labelIndex = this.labelList.indexOf(label);
        if (labelIndex != -1) {
            return this.content.get(labelIndex);
        }
        return null;
    }

    public Iterator<ArrayList<String>> iterator() {
        return content.iterator();
    }

    @Override
    public String toString() {
        StringBuilder aux = new StringBuilder();
        int tab_size = 4;
        int i, j, n_blanks;
        for (String label : labelList){
            aux.append(label);
            n_blanks = tab_size * 8 - label.length();
            for (j = 0; (n_blanks - j) > tab_size; j += tab_size){
                aux.append("\t");
            }
        }
        aux.append("\n");
        for (i = 0; i < size(); i++){
            for (ArrayList<String> col : content){
                aux.append(col.get(i));
                n_blanks = tab_size * 8 - col.get(i).length();
                for (j = 0; (n_blanks - j) > tab_size; j += tab_size){
                    aux.append("\t");
                }
            }
            aux.append("\n");
        }
        return aux.toString();
    }

}
