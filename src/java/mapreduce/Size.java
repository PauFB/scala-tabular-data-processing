package mapreduce;

import factory.DataFrame;

import java.util.function.Function;

public class Size implements Function<DataFrame, Integer> {

    @Override
    public Integer apply(DataFrame dataFrame) {
        return dataFrame.size();
    }

}
