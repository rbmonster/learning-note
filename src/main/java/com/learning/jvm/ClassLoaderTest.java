package com.learning.jvm;

import java.io.InputStream;

/**
 * <pre>
 * @Description:
 * 使用类加载器实现类加载的动作
 * </pre>
 *
 * @version v1.0
 * @ClassName: ClassLoaderTest
 * @Author: sanwu
 * @Date: 2020/7/26 21:17
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String filename = name.substring(name.lastIndexOf(".") +1) + ".class";
                    InputStream in = getClass().getResourceAsStream(filename);
                    if (in == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[in.available()];
                    in.read(b);
                    return defineClass(name,b,0, b.length);
                }catch (Exception e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj = myLoader.loadClass("com.learning.jvm.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof ClassLoaderTest);
    }

}
