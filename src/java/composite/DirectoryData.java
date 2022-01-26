package composite;

import factory.Data;
import factory.DataFrame;
import visitor.Visitor;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;

public class DirectoryData implements DataFrame {

    private final String name;
    private final List<DataFrame> children;

    public DirectoryData(String directoryPath) {
        name = directoryPath;
        children = new LinkedList<>();

        FileData f;
        DirectoryData d;
        File directory = new File(directoryPath);
        File[] archives = directory.listFiles();
        if (archives != null) {
            for (File file : archives) {           // For every archive that contains a DataFrame in directoryPath, add it to the list of children
                if (!file.isDirectory()) {
                    f = new FileData(file.getAbsolutePath());
                    if (f.getData() != null) {
                        children.add(f);
                    }
                } else {
                    d = new DirectoryData(file.getAbsolutePath());
                    if (!d.getChildren().isEmpty()) {
                        children.add(d);
                    }
                }
            }
        } else {
            System.out.println("Directory is empty");
        }
    }

    public List<DataFrame> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }

    public String at(int id, String label) {
        for (DataFrame child : children) {
            if (id > child.size() - 1) {
                id -= child.size();
            } else {
                return child.at(id, label);
            }
        }
        return null;
    }

    public String iat(int i, int j) {
        for (DataFrame child : children) {
            if (child.size() - 1 < i) {
                i -= child.size();
            } else {
                return child.iat(i, j);
            }
        }
        return null;
    }

    public int columns() {
        return this.getLabelList().size();
    }

    public int size() {
        int result = 0;
        for (DataFrame child : this.children)
            result += child.size();
        return result;
    }

    public ArrayList<String> sort(String label, Comparator<String> c) {
        ArrayList<String> temp = new ArrayList<>(getColumn(label));
        if (!temp.isEmpty()) {
            temp.sort(c);
            return temp;
        }
        return null;
    }

    public Data query(String label, Predicate<String> predicate) {
        Data result = new Data(getLabelList(), new LinkedList<>());
        boolean firstHasBeenAdded = false;
        for (DataFrame child : children) {                      // For every child
            if (!firstHasBeenAdded) {
                if (child.query(label, predicate) != null) {
                    result = child.query(label, predicate);     // result points to the first query result
                    firstHasBeenAdded = true;
                }
            } else if (child.query(label, predicate) != null) {
                for (int i = 0; i < result.getContent().size(); i++) {
                    // Add each column of each query() result to the corresponding column of the accumulated (directory) result
                    result.getContent().get(i).addAll(child.query(label, predicate).getContent().get(i));
                }
            }
        }
        return result;
    }

    public LinkedList<ArrayList<String>> getContent() {
        LinkedList<ArrayList<String>> content = new LinkedList<>();
        for (int i = 0; i < getLabelList().size(); i++)
            content.add(new ArrayList<>());
        for (DataFrame child : children) {                          // For every child,
            for (int i = 0; i < content.size(); i++) {
                content.get(i).addAll(child.getContent().get(i));   // accumulate the content of each DataFrame contained in this directory
            }
        }
        return content;
    }

    public LinkedList<String> getLabelList() {
        LinkedList<String> labelList = new LinkedList<>();
        LinkedList<String> childLabelList;
        for (DataFrame child : children) {              // For every child,
            childLabelList = child.getLabelList();
            for (String s : childLabelList) {
                if (!labelList.contains(s)) {
                    labelList.add(s);                   // add each label that is not already contained in labelList
                }
            }
        }
        return labelList;
    }

    public ArrayList<String> getColumn(String label) {
        ArrayList<String> column = new ArrayList<>();
        for (DataFrame child : children) {                  // For every child,
            if (child.getColumn(label) != null) {
                column.addAll(child.getColumn(label));      // accumulate the elements of the column indexed by label
            }
        }
        return column;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Iterator<ArrayList<String>> iterator() {
        return getContent().iterator();
    }

    @Override
    public String toString() {
        StringBuilder aux = new StringBuilder();
        for (DataFrame child : children) {
            aux.append(child.toString()).append("\n");
        }
        return aux.toString();
    }

}
