package com.learning.design.chain;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: SpamHandler
 * @Author: sanwu
 * @Date: 2020/7/9 23:23
 */
public class SpamHandler extends AbstractHandler {
    @Override
    void dealRequest(String request) {
        System.out.println("spam ignore request~");
    }
}
