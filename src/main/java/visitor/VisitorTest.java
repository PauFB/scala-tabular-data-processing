package visitor;

import composite.*;
import factory.DataFrame;

import java.util.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class VisitorTest {

    static FileData csv;
    static FileData txt;
    static DirectoryData dir1;
    static DirectoryData dir2;

    static List<DataFrame> list;

    Visitor v;

    @BeforeAll
    static void Initialize(){
        csv = new FileData("src/main/resources/ages.csv");
        txt = new FileData("src/main/resources/example.txt");
        dir1 = new DirectoryData("src/main/resources/dir1");
        dir2 = new DirectoryData("src/main/resources/dir2");

        list = Arrays.asList(csv, txt, dir1, dir2);
    }

    @BeforeEach
    public void Separate(){
        System.out.println("\n");
    }

    @Test
    public void Maximum(){
        v = new MaximumVisitor("SortOrder");
        List<Double> maximums = new LinkedList<>();
        for (DataFrame d : list) {
            d.accept(v);
            maximums.add(v.getResult());
        }
        System.out.println("list MaximumVisitor(SortOrder) = " + maximums);

        List<Double> expectedMaximums = new LinkedList<>();
        expectedMaximums.add(148.0);
        expectedMaximums.add(3.0);
        expectedMaximums.add(148.0);
        expectedMaximums.add(null);

        assertEquals(expectedMaximums,maximums);
    }


    @Test
    public void Minimum(){
        v = new MinimumVisitor("SortOrder");
        List<Double> minimums = new LinkedList<>();
        for (DataFrame d : list) {
            d.accept(v);
            minimums.add(v.getResult());
        }
        System.out.println("list MinimumVisitor(SortOrder) = " + minimums);

        List<Double> expectedMinimums = new LinkedList<>();
        expectedMinimums.add(1.0);
        expectedMinimums.add(1.0);
        expectedMinimums.add(1.0);
        expectedMinimums.add(null);

        assertEquals(expectedMinimums,minimums);
    }

    @Test
    public void Sum(){
        v = new SumVisitor("SortOrder");
        List<Double> sums = new LinkedList<>();
        for (DataFrame d : list) {
            d.accept(v);
            sums.add(v.getResult());
        }
        System.out.println("list SumVisitor(SortOrder) = " + sums);

        List<Double> expectedSums = new LinkedList<>();
        expectedSums.add(11026.0);
        expectedSums.add(6.0);
        expectedSums.add(11032.0);
        expectedSums.add(null);

        assertEquals(expectedSums,sums);
    }

    @Test
    public void Average(){
        v = new AverageVisitor("SortOrder");
        List<Double> averages = new LinkedList<>();
        for (DataFrame d : list) {
            d.accept(v);
            averages.add(v.getResult());
        }
        System.out.println("list AverageVisitor(SortOrder) = " + averages);

        List<Double> expectedAverages = new LinkedList<>();
        expectedAverages.add(74.5);
        expectedAverages.add(2.0);
        expectedAverages.add(73.05960264900662);
        expectedAverages.add(null);

        assertEquals(expectedAverages,averages);
    }
}