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

    public ArrayList<String> sort(String label, Comparator<Object> c) {
        return data.sort(label,c);
    }

    public DataFrame query(String label, Predicate<String> func) {
        if (data.query(label,func) != null){
            return new TXTData(data.query(label,func));
        }
        return null;
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

    public LinkedList<ArrayList<String>> getContent() {
        return data.getContent();
    }

    public ArrayList<String> getColumn(String label) {
        return data.getColumn(label);
    }

    public void accept(Visitor v, String label) {}

    public Iterator<ArrayList<String>> iterator() {
        return data.iterator();
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
