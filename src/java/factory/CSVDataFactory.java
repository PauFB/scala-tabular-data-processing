package factory;

public class CSVDataFactory implements DataFrameFactory {

    public DataFrame readFile(String fileName) {
        return new CSVData(fileName);
    }

}
