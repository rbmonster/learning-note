package com.learning.jvm;

/**
 * <pre>
 * @Description:
 *  不符合程序次序
 *  不符合管道锁定
 *  不符合volatile
 *  不符合线程的相关原则
 *
 *  预期中A的操作时间先于B，结果应都为123
 *  结果出现不少为0的记录，此时线程不安全。
 * </pre>
 *
 * @version v1.0
 * @ClassName: HappenBefore
 * @Author: sanwu
 * @Date: 2020/7/23 14:21
 */
public class HappenBefore {

    private class Apple{
        private  int value = 0;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

    }

    protected class SetThread implements Runnable {
        private Apple apple;
        public SetThread(Apple apple) {
            this.apple = apple;
        }

        @Override
        public void run() {
            apple.setValue(123);
        }
    }

    protected class GetThread implements Runnable {
        private Apple apple;

        public GetThread(Apple apple) {
            this.apple = apple;
        }

        @Override
        public void run() {
            System.out.println(apple.getValue());
        }
    }

    public void testSetAndGet() {
        Apple apple = new Apple();
        Thread thread1 =new Thread(new SetThread(apple));
        Thread thread2 = new Thread(new GetThread(apple));
        thread1.start();
        thread2.start();
    }

    public static void main(String[] args) {
        HappenBefore happenBefore = new HappenBefore();
        for (int i = 0; i < 100; i++) {
            happenBefore.testSetAndGet();
        }
    }
}

