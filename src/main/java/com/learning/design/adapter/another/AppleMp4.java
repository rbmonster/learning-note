package com.learning.design.adapter.another;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: AppleMp4
 * @Author: sanwu
 * @Date: 2020/5/10 23:48
 */
public class AppleMp4 implements Mp4 {
    @Override
    public void play() {
        System.out.println("this is apple Mp4!");
    }
}
