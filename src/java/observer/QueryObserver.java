package observer;

import java.util.LinkedList;

public class QueryObserver implements Observer {

    private final LinkedList<String> log = new LinkedList<>();

    public void update(String method) {
        if (method.equals("query")) {
            log.add(method);
        }
    }

    @Override
    public String toString() {
        return log.toString();
    }

}
