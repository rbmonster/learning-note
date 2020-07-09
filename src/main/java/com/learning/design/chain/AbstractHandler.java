package com.learning.design.chain;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: AbstractHandler
 * @Author: sanwu
 * @Date: 2020/7/9 23:19
 */
public abstract class AbstractHandler {
    private AbstractHandler next;

    public void handleRequest(String request){
        dealRequest(request);
        next.dealRequest(request);
    }

    abstract void dealRequest(String request);

    public void setNext(AbstractHandler next){
        this.next = next;
    }

    public AbstractHandler getNext() {
        return next;
    }
}
