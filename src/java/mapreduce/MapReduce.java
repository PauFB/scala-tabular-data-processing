package mapreduce;

import factory.Data;
import factory.DataFrame;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapReduce {

    public static <T> List<T> map(List<DataFrame> list, Function<DataFrame, T> function) {
        return list.parallelStream().map(function).collect(Collectors.toList());
    }

    public static <T> T reduce(List<T> list) {
        T object = list.get(0);                             // Get type of T;
        if (object instanceof Integer) {                    // if it's Integer (size() or columns() were called)
            Double sum = 0.0;
            for (T elem : list) {
                sum += (Integer) elem;
            }
            return (T) Double.valueOf(sum / list.size());  // Return the average
        }
        if (object instanceof Data) {                       // If it's Data (query() was called)
            Data result = null;
            boolean firstHasBeenAdded = false;
            for (T dataframe : list) {
                if (!firstHasBeenAdded) {
                    if (dataframe != null) {
                        result = (Data) dataframe;          // result points to the first DataFrame in the structure
                        firstHasBeenAdded = true;
                    }
                } else if (dataframe != null) {
                    for (int i = 0; i < result.getContent().size(); i++) {                          // Add all columns of every query() result
                        result.getContent().get(i).addAll(((Data) dataframe).getContent().get(i));  // to the corresponding column of the resulting DataFrame
                    }
                }
            }
            return (T) result;
        }
        return null;
    }

}
