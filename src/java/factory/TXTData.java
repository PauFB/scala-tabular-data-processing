package factory;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import visitor.Visitor;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;

public class TXTData implements DataFrame {

    Data data;

    public TXTData(String fileName) {
        LinkedList<String> labelList = new LinkedList<>();
        LinkedList<ArrayList<String>> content = new LinkedList<>();

        CsvParserSettings settings = new CsvParserSettings();
        settings.detectFormatAutomatically();

        CsvParser parser = new CsvParser(settings);
        List<String[]> rows = parser.parseAll(new File(fileName));      // Parsing the file

        // Read the header (list of labels)
        for (String label : rows.get(0)) {                              // For each label, create a new empty column
            content.add(new ArrayList<>());                             // and add the label to labelList
            labelList.add(label);
        }

        for (int i = 1; i < rows.size(); i++) {                         // For every row
            String[] line = rows.get(i);
            for (int j = 0; j < line.length; j++) {                     // For every column,
                content.get(j).add(line[j]);                            // add the element to the DataFrame's content
            }
        }

        data = new Data(labelList, content);                            // Initialize the contained data
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

    public void accept(Visitor v) {
    }

    public Iterator<ArrayList<String>> iterator() {
        return data.iterator();
    }

    @Override
    public String toString() {
        return data.toString();
    }

}
