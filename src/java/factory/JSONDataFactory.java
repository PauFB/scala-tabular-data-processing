package factory;

public class JSONDataFactory implements DataFrameFactory {

    public DataFrame readFile(String fileName) {
        return new JSONData(fileName);
    }
}
