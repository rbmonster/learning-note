package com.learning.jvm.memory;

import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 * @Description:
 * 运行时常量池溢出导致内存溢出 jdk7中
 *  参数： -Xmx6M -XX:MaxMetaspaceSize=6M -XX:PermSize=6M
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
