package lesson7.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainL7 {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        start(TestClass.class);
    }

    public static void start(Class classStart) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = classStart.getDeclaredMethods();
        List<Method> methodList = new ArrayList<Method>();


        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                int priority = method.getAnnotation(Test.class).priority();
                if (priority < 1 || priority > 10) throw new RuntimeException("Priority exception!");
                methodList.add(method);
            }
        }

        methodList.sort(new Comparator<Method>() {
            @Override
            public int compare(Method method1, Method method2) {
                return method2.getAnnotation(Test.class).priority() - method1.getAnnotation(Test.class).priority();
            }
        });

        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (methodList.get(0).isAnnotationPresent(BeforeSuite.class))
                    throw new RuntimeException("BeforeSuite exception");
                methodList.add(0, method);
            }
            if (method.isAnnotationPresent(AfterSuite.class)) {
                if (methodList.get(methodList.size() - 1).isAnnotationPresent(AfterSuite.class))
                    throw new RuntimeException("AfterSuite exception");
                methodList.add(method);
            }
        }
        for (Method o : methodList) {
            o.invoke(null);
        }
    }
}


