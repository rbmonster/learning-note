package com.learning.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Step
 * @Author: sanwu
 * @Date: 2020/6/10 22:44
 */
@Getter
@Setter
public class Step {
    private int leg_index;
    private int distance;
    private int duration;
    private int direction;
    private int turn;
    private int road_type;
    private String road_types;
    private String instruction;
    private String path;
    @JsonProperty("traffic_condition")
    private List<TrafficCondition> trafficConditions;
    @JsonProperty("start_location")
    private Origin origin;

    @JsonProperty("end_location")
    private Destination destination;
}
