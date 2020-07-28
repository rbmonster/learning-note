package com.learning.jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

/**
 * <pre>
 * @Description:
 * 使用invoke访问祖类方法
 * </pre>
 *
 * @version v1.0
 * @ClassName: GrandFatherInvoke
 * @Author: sanwu
 * @Date: 2020/7/28 23:41
 */
public class GrandFatherInvoke {
    class GrandFather{
        void thinking(){
            System.out.println("i am grandFather!");
        }
    }

    class Father extends GrandFather {
        void thinking(){
            System.out.println("i am Father!");
        }
    }

    class Son extends Father {
        void thinking(){
//            System.out.println("i am Son!");

            try {
                // 旧的调用方式，jdk7之前视为一个安全性缺陷，因为findSpecial()查找方法版本受到访问约束，应与虚拟机使用的invokespecial命令保持精确对等，才能。
//                MethodType mt = MethodType.methodType(void.class);
//                MethodHandle mh = MethodHandles.lookup().findSpecial(GrandFather.class,"thinking", mt, getClass());
//                mh.invoke(this);


                MethodType mt = MethodType.methodType(void.class);
                Field lookupImpl = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                lookupImpl.setAccessible(true);
                MethodHandle mh = ((MethodHandles.Lookup)lookupImpl.get(null))
                        .findSpecial(GrandFather.class,"thinking", mt, GrandFather.class);
                mh.invoke(this);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        (new GrandFatherInvoke().new Son()).thinking();
    }

}

