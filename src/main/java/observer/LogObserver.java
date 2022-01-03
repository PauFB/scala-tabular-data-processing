package observer;

import java.util.LinkedList;

public class LogObserver implements Observer {

    private LinkedList<String> log = new LinkedList<>();

    @Override
    public void update(String method) {
        this.log.add(method);
    }

    @Override
    public String toString() {
        /*
        String str = "";
        for (String method : this.log) {
            str += method + "\n";
        }
        return str.substring(0, str.length() - 1);
         */
        return this.log.toString();
    }

}
