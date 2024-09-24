package gc;

/**
 * @author tangxinxing
 * @version 1.0
 * @description 对象优先在Eden区分配，Eden没有空闲空间，会触发MinorGC。将对象移到Survivor（如果空间够），不然按分配担保机制进入老年代。
 * @date 2024/9/23
 */
public class EdenSpace {
    private static final int _1MB = 1024 * 1024;

    /**
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * */
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];
    }

    /*
    [GC (Allocation Failure) [PSYoungGen: 6476K->824K(9216K)] 6476K->4928K(19456K), 0.0040390 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
    Heap
     PSYoungGen      total 9216K, used 7207K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
      eden space 8192K, 77% used [0x00000000ff600000,0x00000000ffc3bf70,0x00000000ffe00000)
      from space 1024K, 80% used [0x00000000ffe00000,0x00000000ffece010,0x00000000fff00000)
      to   space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
     ParOldGen       total 10240K, used 4104K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
      object space 10240K, 40% used [0x00000000fec00000,0x00000000ff002020,0x00000000ff600000)
     Metaspace       used 3055K, capacity 4486K, committed 4864K, reserved 1056768K
      class space    used 324K, capacity 386K, committed 512K, reserved 1048576K
    * */
}
