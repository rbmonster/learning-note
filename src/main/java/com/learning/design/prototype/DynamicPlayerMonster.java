package com.learning.design.prototype;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: DynamicPlayerMonster
 * @Author: sanwu
 * @Date: 2020/7/10 0:13
 */
public class DynamicPlayerMonster implements Monster {
    @Override
    public void say() {
        System.out.println("dynamic player monster say");
    }
}
