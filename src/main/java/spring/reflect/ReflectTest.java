package spring.reflect;

import com.sun.org.apache.bcel.internal.generic.LoadClass;
import entity.Car;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * description: 反射测试类
 *
 * @author : LIUTAO
 * create at : 2020/4/25 下午3:16
 **/
public class ReflectTest {
    public static void main(String[] args) {
        Car car = initByReflect();

        String s = car.toString();
        System.out.println(s);
    }

    private static Car initByReflect() {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        Class<?> loadClass;
        try {
            loadClass = contextClassLoader.loadClass("entity.Car");
            Constructor<?> constructor = loadClass.getDeclaredConstructor((Class[]) null);
            Car car  = (Car) constructor.newInstance();
            Method setBrand = loadClass.getMethod("setBrand", String.class);
            setBrand.invoke(car, "tesla");
            return car;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
