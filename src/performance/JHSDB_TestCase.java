package performance;

/**
 * @author tangxinxing
 * @version 1.0
 * @description
 * @date 2024/12/2
 */
/**
 * staticObj、instanceObj、localObj存放在哪里？
 */
public class JHSDB_TestCase {
    static class Test {
        static ObjectHolder staticObj = new ObjectHolder();
        ObjectHolder instanceObj = new ObjectHolder();

        void foo() {
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done"); // 这里设一个断点
        }
    }

    private static class ObjectHolder {
    }

    public static void main(String[] args) {
        Test test = new JHSDB_TestCase.Test();
        test.foo();
    }

    /**
     * JVM参数：-Xmx10m -XX:+UseSerialGC -XX:-UseCompressedOops
     *
     * 命令行：
     * 1. jps -l 找到进程id
     * 2. jhsdb hsdb --pid {pid}
     *
     * 三个对象：
     * 0x0000016c99e620f0	Oop for performance/JHSDB_TestCase$ObjectHolder
     * 0x0000016c99e62118	Oop for performance/JHSDB_TestCase$ObjectHolder
     * 0x0000016c99e62128	Oop for performance/JHSDB_TestCase$ObjectHolder
     *
     * 1. 0x0000016c99e620f0: staticObj
     *  - 并引用了Oop for java/lang/Class @ 0x0000016c99e60a10，查看这个地址也在heap。
     *  说明HotSpot虚拟机用堆区来存储Class对象和静态变量，但类的元数据仍然存储在方法区（JDK7永久代，JDK8 MetaSpace）。
     * 2. 0x0000016c99e62118：instanceObj 指针指向：performance/JHSDB_TestCase$Test @ 0x0000016c99e62100 的 instanceObj字段
     * 3. 0x0000016c99e62128：堆区找不到。在main thread frame找到改地址。该地址引用了一个在NewGen （新生代）performance/JHSDB_TestCase$ObjectHolder的对象。
     */
}
