package factory;

public class TXTDataFactory implements DataFrameFactory {

    public DataFrame readFile(String fileName) {
        return new TXTData(fileName);
    }

}
