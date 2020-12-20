package com.learning.basic.java;

import java.text.SimpleDateFormat;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: ThreadLocalHolderTest
 * @Author: sanwu
 * @Date: 2020/12/18 16:48
 */
public class ThreadLocalHolderTest {

    public static void main(String[] args) {
        ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = ThreadLocal.withInitial(()-> new SimpleDateFormat());
    }
}
