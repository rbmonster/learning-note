package com.learning.jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * <pre>
 * @Description:
 * invoke 调用的示例
 * </pre>
 *
 * @version v1.0
 * @ClassName: MethodHandleTest
 * @Author: sanwu
 * @Date: 2020/7/28 23:30
 */
public class MethodHandleTest {
    static class ClassA {
        public void println(String s) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws Throwable {
        Object obj = System.currentTimeMillis()%2 ==0? System.out: new ClassA();
        getPrintlnMH(obj).invokeExact("sdfasdf");
    }

    private static MethodHandle getPrintlnMH(Object reveiver) throws NoSuchMethodException, IllegalAccessException {
//        MethodHandles
        MethodType mt = MethodType.methodType(void.class, String.class);
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        return lookup.findVirtual(reveiver.getClass(), "println", mt).bindTo(reveiver);
    }
}
