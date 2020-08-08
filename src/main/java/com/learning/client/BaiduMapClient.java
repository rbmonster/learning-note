package com.learning.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <pre>
 * 描述：百度定位地址解析获取
 * http://api.map.baidu.com/geocoding/v3/?address=%E4%B8%8A%E6%B5%B7%E5%B8%82&output=json&ak=tvjX68wYlVlHM0acgnPxhuqnyj3mBpBx&callback=sh
 * </pre>
 *
 * @类名：com.xy.tms.api.baiduMap.BaiduMapClient
 * @作者： Aziz
 * @创建日期: 2020/7/23 14:56
 */
@FeignClient(name = "baidu-map-service", url = "http://api.map.baidu.com")
public interface BaiduMapClient {

    /**
     * 根据地址获取经纬度
     *
     * @param address
     * @param output
     * @param ak
     * @return
     */
    @GetMapping(path = "/geocoding/v3/")
    String getLatitudeAndLongitude(@RequestParam("address") String address, @RequestParam("output") String output, @RequestParam("ak") String ak);


    /**
     * 获取两地址之间的行驶路径
     *
     * @param origin
     * @param destination
     * @param ak
     * @return
     */
    @GetMapping(path = "/directionlite/v1/driving")
    String getRoad(@RequestParam("origin") String origin, @RequestParam("destination") String destination, @RequestParam("ak") String ak);

    /**
     * 根据经纬度获取地址
     *
     * @param ak
     * @param output
     * @param coordtype
     * @param location
     * @return
     */
    @GetMapping(path = "/reverse_geocoding/v3/")
    String getAddressByLatAndLtude(@RequestParam("ak") String ak, @RequestParam("output") String output, @RequestParam("coordtype") String coordtype, @RequestParam("location") String location);
}



