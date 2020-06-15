package com.learning.client;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 描述：TODO
 * </pre>
 *
 * @类名：com.xy.tms.baiDuPosition.entity.AddressComponent
 * @作者： ht_hbs
 * @创建日期: 2019/9/2 19:07
 */
@Getter
@Setter
public class AddressComponent {
    private String country;
    private String country_code;
    private String country_code_iso;
    private String country_code_iso2;
    private String province;
    private String city;
    private String city_level;
    private String district;
    private String town;
    private String adcode;
    private String street;
    private String street_number;
    private String direction;
    private String distance;
}