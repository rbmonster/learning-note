package com.learning.design.adapter.another;

import org.springframework.util.Assert;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Adapter
 * @Author: sanwu
 * @Date: 2020/5/10 23:49
 */
public class Adapter implements Target{
    private Mp4 mp4;
    public Adapter(Mp4 mp4){
        this.mp4 = mp4;
    }

    @Override
    public void invokeRequest() {
        Assert.notNull(mp4,"mp4 is null please initial!");
        mp4.play();
    }
}
