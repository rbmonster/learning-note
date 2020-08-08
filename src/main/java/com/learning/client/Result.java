package com.learning.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <pre>
 * 描述：TODO
 * </pre>
 *
 * @类名：com.xy.tms.baiDuPosition.entity.Result
 * @作者： ht_hbs
 * @创建日期: 2019/9/2 19:04
 */
@Getter
@Setter
@Data
public class Result {
    private Location location;
    private int precise;
    private int confidence;
    private int comprehension;
    private String level;
    private String formatted_address;
    private String business;
    private AddressComponent addressComponent;
    private List<PoiRegions> poiRegions;
    private List<Roads> roads;
    private List<Pois> pois;
    private String sematic_description;
    private int cityCode;

}