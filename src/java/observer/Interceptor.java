package observer;

import factory.DataFrame;

import java.lang.reflect.Method;
import java.util.*;

public class Interceptor implements java.lang.reflect.InvocationHandler {

    private final Object subject;
    private final List<Observer> observerList = new LinkedList<>();

    public Interceptor(DataFrame subject) {
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

        return method.invoke(this.subject, args);

    }
}
