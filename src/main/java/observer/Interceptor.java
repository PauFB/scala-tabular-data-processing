package observer;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class Interceptor implements java.lang.reflect.InvocationHandler {

    private Object subject;
    private List<Observer> observerList = new LinkedList<>();

    public Interceptor(Object subject) {
        this.subject = subject;
    }

    public void addObserver(Observer observer) {
        this.observerList.add(observer);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        for (Observer observer : observerList) {
            observer.update(method.getName());
        }

        /*
        if (method.getDeclaringClass() == DataFrame.class) {

            switch (method.getName()) {

                case "columns":
                    System.out.println("Interceptor (InvocationHandler) has intercepted columns()");
                    break;

                case "size":
                    System.out.println("Interceptor (InvocationHandler) has intercepted size()");
                    break;

            }

        }
        */

        return method.invoke(this.subject, args);

    }

}
