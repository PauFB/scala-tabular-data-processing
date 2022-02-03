package factory;

import java.util.*;
import java.util.function.Predicate;

public class Data {

    LinkedList<String> labelList;
    LinkedList<ArrayList<String>> content;

    public Data(LinkedList<String> labelList, LinkedList<ArrayList<String>> content) {
        this.labelList = labelList;
        this.content = content;
    }

    public String at(int id, String label) {
        int labelIndex = labelList.indexOf(label);

        if (labelIndex != -1 && id < size()) {
            return content.get(labelIndex).get(id);
        }
        return null;
    }

    public String iat(int i, int j) {
        if (i < size() && j < columns()) {
            return content.get(j).get(i);
        }
        return null;
    }

    public int columns() {
        return labelList.size();
    }

    public int size() {
        if (!content.isEmpty()){
            return content.get(0).size();
        }
        return 0;
    }

    public ArrayList<String> sort(String label, Comparator<String> c) {
        int labelIndex = labelList.indexOf(label);

        if (labelIndex != -1) {
            ArrayList<String> temp = new ArrayList<>(content.get(labelIndex));
            temp.sort(c);
            return temp;
        }
        return null;
    }

    public Data query(String label, Predicate<String> func) {
        int col = labelList.indexOf(label);

        if (col != -1) {
            List<String> filtered_column = content.get(col).stream().filter(func).toList();     // Filter the column indexed by label

            if (!filtered_column.isEmpty()) {
                LinkedList<ArrayList<String>> aux = new LinkedList<>();
                for (int k = 0; k < columns(); k++) {
                    aux.add(new ArrayList<>());
                }

                for (int j = 0; j < size(); j++) {                                              // For every line
                    if (filtered_column.contains(content.get(col).get(j))) {                    // If an element in the original column also exists in filtered_column
                        for (int i = 0; i < columns(); i++) {
                            aux.get(i).add(content.get(i).get(j));                              // Add it to aux
                        }
                    }
                }

                return new Data(labelList, aux);
            }
        }
        return new Data(labelList, new LinkedList<>());
    }

    public LinkedList<ArrayList<String>> getContent() {
        return content;
    }

    public LinkedList<String> getLabelList() {
        return labelList;
    }

    public ArrayList<String> getColumn(String label) {
        int labelIndex = labelList.indexOf(label);
        if (labelIndex != -1) {
            return content.get(labelIndex);
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
        for (String label : labelList) {
            aux.append(label);
            n_blanks = tab_size * 8 - label.length();
            for (j = 0; (n_blanks - j) > tab_size; j += tab_size) {
                aux.append("\t");
            }
        }
        aux.append("\n");
        for (i = 0; i < size(); i++) {
            for (ArrayList<String> col : content) {
                aux.append(col.get(i));
                n_blanks = tab_size * 8 - col.get(i).length();
                for (j = 0; (n_blanks - j) > tab_size; j += tab_size) {
                    aux.append("\t");
                }
            }
            aux.append("\n");
        }
        return aux.toString();
    }

}
