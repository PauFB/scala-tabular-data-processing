package mapreduce;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import factory.Data;
import factory.DataFrame;

public class MapReduce {

    public static <T> List<T> map(List<DataFrame> list, Function<DataFrame,T> function) {
        return list.parallelStream().map(function).collect(Collectors.toList());
    }

    public static <T> T reduce(List<T> list) {
        T objecte = list.get(0);
        if (objecte instanceof Integer) {
            Double suma = 0.0;
            for (T elem : list) {
                suma += (Integer) elem;
            }
            return (T) Double.valueOf(suma / list.size());
        }
        if (objecte instanceof Data) {
            Data result = null;
            boolean firstHasBeenAdded = false;
            for (T dataframe : list) {
                if (!firstHasBeenAdded) {
                    if (dataframe != null){
                        result = (Data) dataframe /*.clone()*/;
                        firstHasBeenAdded = true;
                    }
                } else if (dataframe != null) {
                    for (int i = 0; i < result.getContent().size(); i++) {
                        result.getContent().get(i).addAll(((Data)dataframe).getContent().get(i));
                    }
                }
            }
            return (T) result;
        }
        return null;
    }

}
