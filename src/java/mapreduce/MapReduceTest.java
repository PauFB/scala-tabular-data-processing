package mapreduce;

import composite.*;
import java.util.*;

import factory.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class MapReduceTest {

    static FileData csv;
    static FileData json;
    static FileData txt;

    static DirectoryData dir1;
    static DirectoryData dir2;

    static List<DataFrame> list;

    @BeforeAll
    static void Initialize(){
        csv = new FileData("src/resources/ages.csv");
        json = new FileData("src/resources/cities.json");
        txt = new FileData("src/resources/example.txt");

        dir1 = new DirectoryData("src/resources/dir1");
        dir2 = new DirectoryData("src/resources/dir2");

        list = Arrays.asList(csv, json, txt, dir1, dir2);
    }

    @BeforeEach
    public void Separate(){
        System.out.println("\n");
    }

    @Test
    public void MapReduceSize(){
        List<Integer> sizes = MapReduce.map(list, new Size());
        System.out.println("List of sizes:");
        for (Integer elem : sizes)
            System.out.println(elem);
        System.out.println("sizes average: " + MapReduce.reduce(sizes));

        List<Integer> expectedSizes = new LinkedList<>();
        expectedSizes.add(148);
        expectedSizes.add(128);
        expectedSizes.add(3);
        expectedSizes.add(151);
        expectedSizes.add(256);
        Double expectedSizeAverage = 137.2;
        assertAll(() -> assertEquals(expectedSizes,sizes),
                () -> assertEquals(expectedSizeAverage,MapReduce.reduce(sizes)));
    }


    @Test
    public void MapReduceColumns(){
        List<Integer> columns = MapReduce.map(list, new Columns());
        System.out.println("List of columns:");
        for (Integer elem : columns)
            System.out.println(elem);
        System.out.println("columns average: " + MapReduce.reduce(columns));

        List<Integer> expectedColumns = new LinkedList<>();
        expectedColumns.add(3);
        expectedColumns.add(10);
        expectedColumns.add(3);
        expectedColumns.add(3);
        expectedColumns.add(10);
        Double expectedSizeAverage = 5.8;
        assertAll(() -> assertEquals(expectedColumns,columns),
                () -> assertEquals(expectedSizeAverage,MapReduce.reduce(columns)));
    }

    @Test
    public void MapReduceQuerys(){
        List<Data> query_list = MapReduce.map(list, new Query("Code", x -> Integer.parseInt(x) > 888));
        System.out.println("List of querys with Code > 888:");
        for (Data elem : query_list)
            System.out.println(elem);
        System.out.println("Result of Code > 888:\n" + MapReduce.reduce(query_list));

        List<Data> query_list2 = MapReduce.map(list, new Query("Code", x -> Integer.parseInt(x) < 0));
        System.out.println("List of querys with Code < 0:");
        for (Data elem : query_list2)
            System.out.println(elem);
        System.out.println("Result of Code < 0:\n" + MapReduce.reduce(query_list2));

        assertAll(() -> assertNotNull(query_list),
                () -> assertNotNull(MapReduce.reduce(query_list)),
                () -> assertNull(MapReduce.reduce(query_list2)));
    }
}
