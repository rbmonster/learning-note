package com.design.apidesign;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Test
 * @Author: sanwu
 * @Date: 2020/10/27 23:45
 */
public class Test {
    public static void main(String[] args) throws Exception {
//        ArrayList<Integer> list = new ArrayList<Integer>();
//        list.add(1);  //这样调用 add 方法只能存储整形，因为泛型类型的实例为 Integer
//        list.getClass().getMethod("add", Object.class).invoke(list, "asd");
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }

        Thread thread = new Thread(()-> {
            System.out.println("thread start~");
            while (true) {
//                    System.out.println("running");
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("finish sleep");
        thread.join();
        thread.interrupt();
        System.out.println("interrupt finish~!");
    }
}
