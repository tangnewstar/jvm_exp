package java_simple;

import java.lang.reflect.Field;
import java.util.Random;

/**
 * @author tangxinxing
 * @version 1.0
 * @description
 * @date 2024/9/24
 */
public class java_you_dont_know {


    public static void main(String[] args) {
//        test_condition_exp();
//        test_null_ptr();
        try {
            test_random_num();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void test_condition_exp() {
        Object o1 = true ? new Integer(1) : new Double(2.0);
        Object o2;

        if (true)
            o2 = new Integer(1);
        else
            o2 = new Double(2.0);
        System.out.println(o1);
        System.out.println(o2);
    }

    static void test_null_ptr() {
        Integer i = new Integer(1);
        if (i.equals(1))
            i = null;
        Double d = new Double(2.0);
        Object o = true ? i : d; // NullPointerException!
        System.out.println(o);
    }

    static void test_random_num() throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException {

        // Extract the IntegerCache through reflection
        Class<?> clazz = Class.forName(
                "java.lang.Integer$IntegerCache");
        Field field = clazz.getDeclaredField("cache");
        field.setAccessible(true);
        Integer[] cache = (Integer[]) field.get(clazz);

        // Rewrite the Integer cache
        for (int i = 0; i < cache.length; i++) {
            cache[i] = new Integer(
                    new Random().nextInt(cache.length));
        }
        // Prove randomness
        for (int i = 0; i < 10; i++) {
            System.out.println((Integer) i);
        }
    }
}
