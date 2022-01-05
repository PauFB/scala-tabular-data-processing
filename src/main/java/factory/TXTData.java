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
        List<String[]> rows = parser.parseAll(new File(fileName));

        for (String label : rows.get(0)) {
            labelList.add(label);
            content.add(new ArrayList<>());
        }

        for (int i = 1; i < rows.size(); i++) {
            String[] line = rows.get(i);
            for (int j = 0; j < line.length; j++) {
                content.get(j).add(line[j]);
            }
        }

        data = new Data(labelList,content);
    }

    public TXTData(Data data) {
        this.data = data;
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
        return this.data.sort(label,c);
    }

    public Data query(String label, Predicate<String> func) {
        return data.query(label,func);
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

    public LinkedList<ArrayList<String>> getContent() {
        return this.data.getContent();
    }

    @Override
    public LinkedList<String> getLabelList() {
        return this.data.getLabelList();
    }

    public ArrayList<String> getColumn(String label) {
        return this.data.getColumn(label);
    }

    public void accept(Visitor v) {}

    public Iterator<ArrayList<String>> iterator() {
        return this.data.iterator();
    }

    @Override
    public String toString() {
        return this.data.toString();
    }

}
