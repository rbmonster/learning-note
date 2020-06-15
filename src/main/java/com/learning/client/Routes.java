package com.learning.client;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Routes
 * @Author: sanwu
 * @Date: 2020/6/10 22:27
 */
@Getter
@Setter
public class Routes {
    private double distance;
    private double duration;
    private int traffic_condition;
    private int toll;
    List<Step>steps;
}
