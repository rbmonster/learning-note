package com.learning.jvm.memory;

/**
 * <pre>
 * @Description:
 * 测试虚拟机栈和本地方法栈 模拟堆栈溢出
 * -Xss128k
 * </pre>
 *
 * @version v1.0
 * @ClassName: JavaVMStackSOF
 * @Author: sanwu
 * @Date: 2020/7/29 20:57
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        }catch (Exception e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }

    /**
     * OUTPUT:
     *
     * Exception in thread "main" java.lang.StackOverflowError
     * 	at com.learning.jvm.memory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:18)
     * 	at com.learning.jvm.memory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:19)
     * 	at com.learning.jvm.memory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:19)
     * 	at com.learning.jvm.memory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:19)
     * 	at com.learning.jvm.memory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:19)
     */
}
