package com.learning.design.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: MonsetMaker
 * @Author: sanwu
 * @Date: 2020/7/10 0:14
 */
public class MonsterMaker {
    private static final Map<String,Monster> monsterMap = new HashMap<>();
    public MonsterMaker(){
        monsterMap.put("wellKnow", new WellKnowMonster());
        monsterMap.put("dynamic", new DynamicPlayerMonster());
    }

    public Monster getCloneMonster(String type) {
        return monsterMap.get(type);
    }
}
