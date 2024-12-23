package gc;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author tangxinxing
 * @version 1.0
 * @description
 * @date 2024/8/9
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;
    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
