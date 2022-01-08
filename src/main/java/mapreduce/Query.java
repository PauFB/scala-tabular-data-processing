package mapreduce;

import factory.*;

import java.util.function.Function;
import java.util.function.Predicate;

public class Query implements Function<DataFrame, Data> {

    String label;
    Predicate<String> predicate;

    public Query(String label, Predicate<String> predicate) {
        this.label = label;
        this.predicate = predicate;
    }

    @Override
    public Data apply(DataFrame dataFrame) {
        return dataFrame.query(label,predicate);
    }
}
