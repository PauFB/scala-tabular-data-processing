package mapreduce;

import factory.DataFrame;

import java.util.function.Function;
import java.util.function.Predicate;

public class Query implements Function<DataFrame, DataFrame> {

    String label;
    Predicate<String> predicate;

    public Query(String label, Predicate<String> predicate) {
        this.label = label;
        this.predicate = predicate;
    }

    @Override
    public DataFrame apply(DataFrame dataFrame) {
        return dataFrame.query(label,predicate);
    }
}
