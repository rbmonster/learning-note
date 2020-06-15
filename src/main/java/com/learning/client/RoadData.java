package com.learning.client;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: RoadData
 * @Author: sanwu
 * @Date: 2020/6/10 22:52
 */
@Getter
@Setter
public class RoadData {
    private int status;
    private String message;
    private RoadResult result;
}
