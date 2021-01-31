package com.learning.mybatis.dao;

import com.learning.mybatis.entity.Demo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <pre>
 * @Description:
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: DemoMapper
 * @Author: sanwu
 * @Date: 2021/1/23 17:17
 */
public interface DemoMapper {

    Demo selectByPrimaryKey(Long demoId);

    int insert(Demo demo);

    int updateByPrimaryKeySelective(Demo demo);

    int updateByPrimaryKey(Demo demo);

    List<Demo> queryAll();

    int delete(String demoId);


    List<Demo> selectWithoutParam(Long demoId, String demoName);


//    List<Demo> selectWithoutParamByEntity(@Param("demo") Demo demo);
    List<Demo> selectWithoutParamByEntity(Demo demo);

    List<Demo> selectWithoutParamByEntityTwo (Demo demo1,Demo demo2);
}
