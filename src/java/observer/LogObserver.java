package observer;

import java.util.LinkedList;

public class LogObserver implements Observer {

    private final LinkedList<String> log = new LinkedList<>();

    public void update(String method) {
        log.add(method);
    }

    @Override
    public String toString() {
        return log.toString();
    }

}
