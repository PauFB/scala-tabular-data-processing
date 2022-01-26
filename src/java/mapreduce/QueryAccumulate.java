package mapreduce;

import factory.Data;

import java.util.function.BinaryOperator;

public class QueryAccumulate implements BinaryOperator<Data> {

    @Override
    public Data apply(Data data1, Data data2) {
        if ((!data1.getContent().isEmpty()) && (!data2.getContent().isEmpty())) {
            for (int i = 0; i < data1.getContent().size(); i++) {
                // Add each column of each data2 content to the corresponding column of the data1 content
                data1.getContent().get(i).addAll(data2.getContent().get(i));
            }
        }
        return data1;
    }

}
