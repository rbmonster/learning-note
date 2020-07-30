package com.learning.jvm;

import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * @Description:
 * 任何对象的Finalize 方法只会被系统调用一次，如果下次再垃圾回收，方法不会再调用一次。
 * </pre>
 *
 * @version v1.0
 * @ClassName: FinalizeEscapeGC
 * @Author: sanwu
 * @Date: 2020/7/30 21:56
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes, I am still live!");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        // 自我拯救
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();
        SAVE_HOOK = null;
        System.gc();

        TimeUnit.SECONDS.sleep(1);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no I am dead ~");
        }

        SAVE_HOOK = null;
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no I am dead ~");
        }
    }
}

/**
 * OUTPUT:
 *
 * finalize method executed!
 * yes, I am still live!
 * no I am dead ~
 */
