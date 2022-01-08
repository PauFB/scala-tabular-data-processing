package factory;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class FactoryTest {
	static DataFrameFactory CsvFactory;
	static DataFrame csv;
	static DataFrameFactory JsonFactory;
	static DataFrame json;
	static DataFrameFactory TxtFactory;
	static DataFrame txt;

	@BeforeAll
	static void Initialize(){
		CsvFactory = new CSVDataFactory();
		csv = CsvFactory.readFile("src/main/resources/ages.csv");
		JsonFactory = new JSONDataFactory();
		json = JsonFactory.readFile("src/main/resources/cities.json");
		TxtFactory = new TXTDataFactory();
		txt = TxtFactory.readFile("src/main/resources/example.txt");
	}

	@BeforeEach
	public void Separate(){
		System.out.println("\n");
	}

	@Test
	public void At(){
		System.out.println("csv at(10,SortOrder) = " + csv.at(10,"SortOrder"));
		System.out.println("json at(0,LatD) = " + json.at(0,"LatD"));
		System.out.println("txt at(1000, SortOrder) = " + txt.at(1000,"SortOrder"));
		System.out.println("json at(0, NoLabel) = " + json.at(0,"NoLabel"));
		assertAll(() -> assertEquals("11",csv.at(10,"SortOrder")),
				() -> assertEquals("41",json.at(0,"LatD")),
				() -> assertNull(txt.at(1000,"SortOrder")),
				() -> assertNull(json.at(0,"NoLabel")));
	}

	@Test
	public void Iat(){
		System.out.println("csv iat(10,2) = " + csv.iat(10,2));
		System.out.println("json iat(0,0) = " + json.iat(0,0));
		System.out.println("txt iat(1000,2) = " + txt.iat(1000,2));
		System.out.println("json iat(0,1000) = " + json.iat(0,1000));
		assertAll(() -> assertEquals("11",csv.iat(10,2)),
				() -> assertEquals("41",json.iat(0,0)),
				() -> assertNull(txt.iat(1000,2)),
				() -> assertNull(json.iat(0,1000)));
	}

	@Test
	public void Columns(){
		System.out.println("csv columns() = " + csv.columns());
		System.out.println("json columns() = " + json.columns());
		System.out.println("txt columns() = " + txt.columns());
		assertAll(() -> assertEquals(3,csv.columns()),
				() -> assertEquals(10,json.columns()),
				() -> assertEquals(3,txt.columns()));
	}

	@Test
	public void Size(){
		System.out.println("csv size() = " + csv.size());
		System.out.println("json size() = " + json.size());
		System.out.println("txt size() = " + txt.size());
		assertAll(() -> assertEquals(148,csv.size()),
				() -> assertEquals(128,json.size()),
				() -> assertEquals(3,txt.size()));
	}

	@Test
	public void Sort(){
		System.out.println("csv sort(Code,IntAscending) = " + csv.sort("Code",new IntAscending()));
		System.out.println("json sort(LatD,IntAscending) = " + json.sort("LatD",new IntAscending()));
		System.out.println("txt sort(NoLabel,IntAscending) = " + txt.sort("NoLabel",new IntAscending()));
		assertAll(() -> assertNotNull(csv.sort("Code",new IntAscending())),
				() -> assertNotNull(json.sort("LatD",new IntAscending())),
				() -> assertNull(txt.sort("NoLabel",new IntAscending())));
	}

	@Test
	public void Query(){
		System.out.println("csv Code >= 888:\n" + csv.query("Code", x -> Integer.parseInt(x) >= 888));
		System.out.println("json LatD >= 50:\n" + json.query("LatD", x -> Integer.parseInt(x) >= 50));
		System.out.println("txt NoLabel >= 888:\n" + txt.query("NoLabel", x -> Integer.parseInt(x) >= 888));
		System.out.println("txt Code < 0:\n" + txt.query("Code", x -> Integer.parseInt(x) < 0));
		assertAll(() -> assertNotNull(csv.query("Code",x -> Integer.parseInt(x) >= 888)),
				() -> assertNotNull(json.query("LatD",x -> Integer.parseInt(x) >= 50)),
				() -> assertNull(txt.query("NoLabel",x -> Integer.parseInt(x) >= 888)),
				() -> assertNull(txt.query("NoLabel",x -> Integer.parseInt(x) < 0)));
	}

}
