package gc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tangxinxing
 * @version 1.0
 * @description
 * @date 2024/7/26
 */
public class JavaHeapOom {

    static class OOMObject{

    }
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();

        while(true) {
            list.add(new OOMObject());
        }
    }
}
