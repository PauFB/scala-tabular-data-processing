package observer;

import factory.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Proxy;

public class ObserverTest {

    static Interceptor interceptor;

    static LogObserver logObserver;
    static QueryObserver queryObserver;

    static DataFrame df;

    @BeforeAll
    static void Initialize(){
        interceptor = new Interceptor(new CSVData("src/main/resources/ages.csv"));
        logObserver = new LogObserver();
        queryObserver = new QueryObserver();
        interceptor.addObserver(logObserver);
        interceptor.addObserver(queryObserver);
        df = (DataFrame) Proxy.newProxyInstance(DataFrame.class.getClassLoader(),
                new Class<?>[] {DataFrame.class},
                interceptor);

        df.columns();
        df.size();
        df.iat(0,0);
        df.query("Code", x -> Integer.parseInt(x) > 888);
        df.max("Code");
        df.query("SortCode", x -> Integer.parseInt(x) < 0);
    }

    @BeforeEach
    public void Separate(){
        System.out.println("\n");
    }

    @Test
    public void LogObserver(){
        System.out.println("df operations made = " + logObserver);
        assertNotNull(logObserver);
    }

    @Test
    public void QueryObserver(){
        System.out.println("df query operations made = " + queryObserver);
        assertNotNull(queryObserver);
    }
}
