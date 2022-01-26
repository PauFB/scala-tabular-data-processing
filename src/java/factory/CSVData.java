package factory;

import visitor.Visitor;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class CSVData implements DataFrame {

    Data data;

    public CSVData(String fileName) {
        try {
            LinkedList<String> labelList = new LinkedList<>();
            LinkedList<ArrayList<String>> content = new LinkedList<>();

            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

            String row = fileReader.readLine();                 // Read the first row (list of labels)
            StringTokenizer st = new StringTokenizer(row, ",");

            // Read the header (list of labels)
            while (st.hasMoreTokens()) {
                content.add(new ArrayList<>());					// For each label, create a new empty column
                labelList.add(st.nextToken());					// and add the label to labelList
            }

            // Read the file's content
            row = fileReader.readLine();
            while (row != null) {                               // For every row
                int i = 0;
                st = new StringTokenizer(row, ",");

                while (st.hasMoreTokens()) {                    // For every column,
                    content.get(i).add(st.nextToken());			// add the element to the DataFrame's content
                    i++;
                }
                row = fileReader.readLine();
            }

            data = new Data(labelList, content);                // Initialize the contained data

            fileReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        } catch (IOException e) {
            System.out.println("I/O Exception: " + e);
        }
    }

    public String at(int id, String label) {
        return data.at(id, label);
    }

    public String iat(int i, int j) {
        return data.iat(i, j);
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

    public Data query(String label, Predicate<String> func) {
        return data.query(label, func);
    }

    public LinkedList<ArrayList<String>> getContent() {
        return data.getContent();
    }

    public LinkedList<String> getLabelList() {
        return data.getLabelList();
    }

    public ArrayList<String> getColumn(String label) {
        return data.getColumn(label);
    }

    public void accept(Visitor v) {}

    public Iterator<ArrayList<String>> iterator() {
        return data.iterator();
    }

    @Override
    public String toString() {
        return data.toString();
    }

}
