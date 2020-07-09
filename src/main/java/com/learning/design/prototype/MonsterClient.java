package com.learning.design.prototype;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: MonsterClient
 * @Author: sanwu
 * @Date: 2020/7/10 0:17
 */
public class MonsterClient {
    public static void main(String[] args) {
        MonsterMaker monsterMaker = new MonsterMaker();
        Monster monster = monsterMaker.getCloneMonster("wellKnow");
        monster.say();
    }
}
