package mapreduce;

import factory.DataFrame;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class MapReduce {

    public static <T> T mapreduce(List<DataFrame> list, Function<DataFrame, T> function, BinaryOperator<T> accumulator) {
        if (list.parallelStream().map(function).reduce(accumulator).isPresent()) {
            return list.parallelStream().map(function).reduce(accumulator).get();
        }
        return null;
    }

}
