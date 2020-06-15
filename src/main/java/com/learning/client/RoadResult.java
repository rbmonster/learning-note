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
 * @ClassName: RoadResult
 * @Author: sanwu
 * @Date: 2020/6/10 22:52
 */
@Getter
@Setter
public class RoadResult {
    private Origin origin;
    private Destination destination;
    private List<Routes> routes;
}
