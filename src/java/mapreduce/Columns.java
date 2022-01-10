package mapreduce;

import factory.DataFrame;

import java.util.function.Function;

public class Columns implements Function<DataFrame, Integer> {

    @Override
    public Integer apply(DataFrame dataFrame) {
        return dataFrame.columns();
    }

}
