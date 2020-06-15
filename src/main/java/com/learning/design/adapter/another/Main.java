package com.learning.design.adapter.another;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Main
 * @Author: sanwu
 * @Date: 2020/5/10 23:51
 */
public class Main {
    public static void main(String[] args) {
        Adapter adapter = new Adapter(new AppleMp4());
        adapter.invokeRequest();
    }
}
