package com.learning.design.chain;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: MassageSender
 * @Author: sanwu
 * @Date: 2020/7/9 23:23
 */
public class MassageSender {
    public static void main(String[] args) {
        AbstractHandler fanHandler = new FanHandler();
        AbstractHandler spamHandler = new SpamHandler();
        fanHandler.setNext(spamHandler);
        fanHandler.handleRequest("customer send a love message");
    }
}
