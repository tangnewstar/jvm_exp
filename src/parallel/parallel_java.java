package parallel;

import java.util.Arrays;
import java.util.List;

/**
 * @author tangxinxing
 * @version 1.0
 * @description
 * @date 2024/9/24
 */
public class parallel_java {

    public static void main(String[] args) {
        List<Integer> numberList = Arrays.asList(1,2,3,4,5,6,7,8,9);
        numberList.parallelStream().forEach(System.out::println);
    }
}
