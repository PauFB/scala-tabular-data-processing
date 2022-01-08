package mapreduce;

import factory.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapReduce {

    public static <T> List<T> map(List<DataFrame> list, Function<DataFrame,T> function) {
        return list.parallelStream().map(function).collect(Collectors.toList());
    }

    public static <T> T reduce(List<T> list) {
        T object = list.get(0);            //Get type of T
        if (object instanceof Integer) {       //If it's an Integer (Size or Columns)
            Double suma = 0.0;
            for (T elem : list) {
                suma += (Integer) elem;
            }
            return (T) Double.valueOf(suma / list.size());  //Return the average
        }
        if (object instanceof Data) {           //If it's a Data (Query)
            Data result = null;
            boolean firstHasBeenAdded = false;
            for (T dataframe : list) {
                if (!firstHasBeenAdded) {
                    if (dataframe != null){
                        result = (Data) dataframe;      //result takes the first
                        firstHasBeenAdded = true;
                    }
                } else if (dataframe != null) {
                    for (int i = 0; i < result.getContent().size(); i++) {
                        result.getContent().get(i).addAll(((Data)dataframe).getContent().get(i));   //Add to the content of result the content of the rest
                    }
                }
            }
            return (T) result;
        }
        return null;
    }

}
