package com.learning.jvm.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * @Description:
 * java 堆内存溢出的异常测试
 *
 * 参数：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * </pre>
 *
 * @version v1.0
 * @ClassName: HeapOOM
 * @Author: sanwu
 * @Date: 2020/7/29 20:53
 */
public class HeapOOM {
    static class OOMObject{

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }

    /**
     * OUTPUT：
     *
     * java.lang.OutOfMemoryError: Java heap space
     * Dumping heap to java_pid28568.hprof ...
     * Heap dump file created [28630881 bytes in 0.115 secs]
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     * 	at java.util.Arrays.copyOf(Arrays.java:3210)
     * 	at java.util.Arrays.copyOf(Arrays.java:3181)
     * 	at java.util.ArrayList.grow(ArrayList.java:261)
     * 	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:235)
     * 	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:227)
     * 	at java.util.ArrayList.add(ArrayList.java:458)
     * 	at com.learning.jvm.memory.HeapOOM.main(HeapOOM.java:25)
     */
}
