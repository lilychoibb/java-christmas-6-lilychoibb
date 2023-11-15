package christmas.view;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class OutputViewProxy implements InvocationHandler {
    private final Object target;

    public OutputViewProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(target, args);
        System.out.println();
        return result;
    }

    public static OutputView createProxy(OutputView target) {
        return (OutputView) Proxy.newProxyInstance(
                OutputView.class.getClassLoader(),
                new Class<?>[]{OutputView.class},
                new OutputViewProxy(target)
        );
    }
}