package performance;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tangxinxing
 * @version 1.0
 * @description
 * @date 2024/12/2
 */
public class JConsoleMemoryTest {
    /**
     * 内存占位符对象，一个OOMObject大约占64KB
     */
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }
    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<OOMObject>();
        for (int i = 0; i < num; i++) {
        // 稍作延时，令监视曲线的变化更加明显
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws Exception {
        fillHeap(1000);
    }

    /**
     * 这段代码的作用是以64KB/50ms的速度向Java堆中填充数据，一共填充1000次，使用JConsole
     * 的“内存”页签进行监视，观察曲线和柱状指示图的变化。
     * JVM参数：-Xms100m -Xmx100m -XX:+UseSerialGC
     *
     * 问题：
     * 1）虚拟机启动参数只限制了Java堆为100MB，但没有明确使用-Xmn参数指定新生代大小，读者
     * 能否从监控图中估算出新生代的容量？
     * EdenSpace：27,328 KB，SurvivorSpace：8:1
     * 新生代 = 27,328 * (8+1)/8 = 34160KB
     *
     * 2）为何执行了System.gc()之后，图4-12中代表老年代的柱状图仍然显示峰值状态，代码需要如何
     * 调整才能让System.gc()回收掉填充到堆中的对象？
     * gc后list仍然保持引用，所以里面的OOMObject仍然存活。fillHeap没有退出前，当前stack仍然持有list对象。
     * gc操作发生在fillHeap外就可以。
     */
}
