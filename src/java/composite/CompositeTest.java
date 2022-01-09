package composite;

import factory.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CompositeTest {

    static FileData csv;
    static FileData json;
    static FileData txt;
    static DirectoryData dir1;
    static DirectoryData dir2;

    @BeforeAll
    static void Initialize(){
        csv = new FileData("src/resources/ages.csv");
        json = new FileData("src/resources/cities.json");
        txt = new FileData("src/resources/example.txt");
        dir1 = new DirectoryData("src/resources/dir1");
        dir2 = new DirectoryData("src/resources/dir2");
    }

    @BeforeEach
    public void Separate(){
        System.out.println("\n");
    }

    @Test
    public void DirectoryAt(){
        System.out.println(dir1.getName() + " at(10, SortOrder) = " + dir1.at(10,"SortOrder"));
        System.out.println(dir2.getName() + " at(128, LatD) = " + dir2.at(128,"LatD"));
        System.out.println(dir1.getName() + " at(1000, SortOrder) = " + dir1.at(1000,"SortOrder"));
        System.out.println(dir2.getName() + " at(128, NoLabel) = " + dir2.at(128,"NoLabel"));
        assertAll(() -> assertEquals("11",dir1.at(10,"SortOrder")),
                () -> assertEquals("41",dir2.at(128,"LatD")),
                () -> assertNull(dir1.at(1000,"SortOrder")),
                () -> assertNull(dir2.at(128,"NoLabel")));
    }

    @Test
    public void DirectoryIat(){
        System.out.println(dir1.getName() + " iat(10,2) = " + dir1.iat(10,2));
        System.out.println(dir2.getName() + " iat(128,0) = " + dir2.iat(128,0));
        System.out.println(dir1.getName() + " iat(1000,2) = " + dir1.iat(1000,2));
        System.out.println(dir2.getName() + " iat(128,1000) = " + dir2.iat(128,1000));
        assertAll(() -> assertEquals("11",dir1.iat(10,2)),
                () -> assertEquals("41",dir2.iat(128,0)),
                () -> assertNull(dir1.iat(1000,2)),
                () -> assertNull(dir2.iat(128,1000)));
    }

    @Test
    public void DirectoryColumns(){
        System.out.println(dir1.getName() + " columns() = " + dir1.columns());
        System.out.println(dir2.getName() + " columns() = " + dir2.columns());
        assertAll(() -> assertEquals(3,dir1.columns()),
                () -> assertEquals(10,dir2.columns()));
    }

    @Test
    public void DirectorySize(){
        System.out.println(dir1.getName() + " size() = " + dir1.size());
        System.out.println(dir2.getName() + " size() = " + dir2.size());
        assertAll(() -> assertEquals(151,dir1.size()),
                () -> assertEquals(256,dir2.size()));
    }

    @Test
    public void DirectorySort(){
        System.out.println(dir1.getName() + " sort(Code,IntAscending) = " + dir1.sort("Code",new IntAscending()));
        System.out.println(dir2.getName() + " sort(LatD,IntAscending) = " + dir2.sort("LatD",new IntAscending()));
        System.out.println(dir1.getName() + " sort(NoLabel,IntAscending) = " + dir1.sort("NoLabel",new IntAscending()));
        assertAll(() -> assertNotNull(dir1.sort("Code",new IntAscending())),
                () -> assertNotNull(dir2.sort("LatD",new IntAscending())),
                () -> assertNull(dir1.sort("NoLabel",new IntAscending())));
    }

    @Test
    public void DirectoryQuery(){
        System.out.println(dir1.getName() + " Code >= 888:\n" + dir1.query("Code", x -> Integer.parseInt(x) >= 888));
        System.out.println(dir2.getName() + " LatD >= 50:\n" + dir2.query("LatD", x -> Integer.parseInt(x) >= 50));
        System.out.println(dir1.getName() + " NoLabel >= 888:\n" + dir1.query("NoLabel", x -> Integer.parseInt(x) >= 888));
        System.out.println(dir1.getName() + " Code < 0:\n" + dir1.query("Code", x -> Integer.parseInt(x) < 0));
        assertAll(() -> assertNotNull(dir1.query("Code",x -> Integer.parseInt(x) >= 888)),
                () -> assertNotNull(dir2.query("LatD",x -> Integer.parseInt(x) >= 50)),
                () -> assertNull(dir1.query("NoLabel",x -> Integer.parseInt(x) >= 888)),
                () -> assertNull(dir1.query("Code",x -> Integer.parseInt(x) < 0)));
    }

    @Test
    public void FileAt(){
        System.out.println(csv.getName() + " at(10,SortOrder) = " + csv.at(10,"SortOrder"));
        System.out.println(json.getName() + " at(0,LatD) = " + json.at(0,"LatD"));
        System.out.println(txt.getName() + " at(1000, SortOrder) = " + txt.at(1000,"SortOrder"));
        System.out.println(json.getName() + " at(0, NoLabel) = " + json.at(0,"NoLabel"));
        assertAll(() -> assertEquals("11",csv.at(10,"SortOrder")),
                () -> assertEquals("41",json.at(0,"LatD")),
                () -> assertNull(txt.at(1000,"SortOrder")),
                () -> assertNull(json.at(0,"NoLabel")));
    }

    @Test
    public void FileIat(){
        System.out.println(csv.getName() + " iat(10,2) = " + csv.iat(10,2));
        System.out.println(json.getName() + " iat(0,0) = " + json.iat(0,0));
        System.out.println(txt.getName() + " iat(1000,2) = " + txt.iat(1000,2));
        System.out.println(json.getName() + " iat(0,1000) = " + json.iat(0,1000));
        assertAll(() -> assertEquals("11",csv.iat(10,2)),
                () -> assertEquals("41",json.iat(0,0)),
                () -> assertNull(txt.iat(1000,2)),
                () -> assertNull(json.iat(0,1000)));
    }

    @Test
    public void FileColumns(){
        System.out.println(csv.getName() + " columns() = " + csv.columns());
        System.out.println(json.getName() + " columns() = " + json.columns());
        System.out.println(txt.getName() + " columns() = " + txt.columns());
        assertAll(() -> assertEquals(3,csv.columns()),
                () -> assertEquals(10,json.columns()),
                () -> assertEquals(3,txt.columns()));
    }

    @Test
    public void FileSize(){
        System.out.println(csv.getName() + " size() = " + csv.size());
        System.out.println(json.getName() + " size() = " + json.size());
        System.out.println(txt.getName() + " size() = " + txt.size());
        assertAll(() -> assertEquals(148,csv.size()),
                () -> assertEquals(128,json.size()),
                () -> assertEquals(3,txt.size()));
    }

    @Test
    public void FileSort(){
        System.out.println(csv.getName() + " sort(Code,IntAscending) = " + csv.sort("Code",new IntAscending()));
        System.out.println(json.getName() + " sort(LatD,IntAscending) = " + json.sort("LatD",new IntAscending()));
        System.out.println(txt.getName() + " sort(NoLabel,IntAscending) = " + txt.sort("NoLabel",new IntAscending()));
        assertAll(() -> assertNotNull(csv.sort("Code",new IntAscending())),
                () -> assertNotNull(json.sort("LatD",new IntAscending())),
                () -> assertNull(txt.sort("NoLabel",new IntAscending())));
    }

    @Test
    public void FileQuery(){
        System.out.println(csv.getName() + " Code >= 888:\n" + csv.query("Code", x -> Integer.parseInt(x) >= 888));
        System.out.println(json.getName() + " LatD >= 50:\n" + json.query("LatD", x -> Integer.parseInt(x) >= 50));
        System.out.println(txt.getName() + " NoLabel >= 888:\n" + txt.query("NoLabel", x -> Integer.parseInt(x) >= 888));
        System.out.println(txt.getName() + " Code < 0:\n" + txt.query("Code", x -> Integer.parseInt(x) < 0));
        assertAll(() -> assertNotNull(csv.query("Code",x -> Integer.parseInt(x) >= 888)),
                () -> assertNotNull(json.query("LatD",x -> Integer.parseInt(x) >= 50)),
                () -> assertNull(txt.query("NoLabel",x -> Integer.parseInt(x) >= 888)),
                () -> assertNull(txt.query("Code",x -> Integer.parseInt(x) < 0)));
    }
}
