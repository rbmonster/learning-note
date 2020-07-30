package com.learning.jvm.memory;

/**
 * <pre>
 * @Description: 目前本地运行无内存溢出问题出现
 * 创建线程导致内存溢出
 *
 * 参数： -Xss2M
 * 设置每个线程的堆栈大小
 * </pre>
 *
 * @version v1.0
 * @ClassName: JavaVMStackOOM
 * @Author: sanwu
 * @Date: 2020/7/29 21:10
 */
public class JavaVMStackOOM {
    private void dontStop() {

    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
