package com.learning.design.chain;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: FanHandler
 * @Author: sanwu
 * @Date: 2020/7/9 23:22
 */
public class FanHandler extends AbstractHandler {
    @Override
    void dealRequest(String request) {
        System.out.println("this is fan deal with request~ :" +request);
    }
}
