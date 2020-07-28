package com.learning.jvm;

/**
 * <pre>
 * @Description:
 * 包装类型自动装猜想测试题
 * </pre>
 *
 * @version v1.0
 * @ClassName: IntegerTest
 * @Author: sanwu
 * @Date: 2020/7/29 0:17
 */
public class IntegerTest {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c==d);
        System.out.println(e==f);
        System.out.println(c == (a+b));
        System.out.println(c.equals(a+b));
        System.out.println(g == (a+b));
        System.out.println(g.equals(a+b));
    }
}
