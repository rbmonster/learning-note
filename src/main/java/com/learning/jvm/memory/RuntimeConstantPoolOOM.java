package com.learning.jvm.memory;

import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 * @Description:
 *  参数： -Xmx6M -XX:MaxMetaspaceSize=6M
 * </pre>
 *
 * @version v1.0
 * @ClassName: RuntimeConstantPoolOOM
 * @Author: sanwu
 * @Date: 2020/7/29 21:17
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}
