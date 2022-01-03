package observer;

import factory.CSVData;
import factory.DataFrame;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {

        Interceptor interceptor = new Interceptor(new CSVData("DimenLookupAge8277.csv"));

        LogObserver logObserver = new LogObserver();
        interceptor.addObserver(logObserver);

        DataFrame df = (DataFrame) Proxy.newProxyInstance(DataFrame.class.getClassLoader(),
                new Class<?>[] {DataFrame.class},
                interceptor);

        int col = df.columns();
        int size = df.size();

        System.out.println(logObserver);

    }

}
