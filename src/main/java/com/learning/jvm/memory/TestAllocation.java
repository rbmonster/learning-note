package com.learning.jvm.memory;

/**
 * <pre>
 * @Description:
 * JVM book 里面的内存分配策略
 *
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: TestAllocation
 * @Author: sanwu
 * @Date: 2020/7/25 20:49
 */
public class TestAllocation {

    private static final  int _1MB =1024* 1024;

    public static void main(String[] args) {
        testHandlePromotion();
    }

    /**
     * 对象优先在Eden中分配
     * -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     *
     */
    public static void testAllocation() {
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[2* _1MB];
        allocation2 = new byte[2* _1MB];
        allocation3 = new byte[2* _1MB];
        allocation4 = new byte[4* _1MB];
    }


    /**
     * 大对象直接进入老年代
     *
     * -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
     * 对象直接进入老年代
     *
     *  tenured generation   total 10240K, used 4096K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     *    the space 10240K,  40% used [0x00000000ff600000, 0x00000000ffa00010, 0x00000000ffa00200, 0x0000000100000000)
     */
    public static void testPretenureSizeThreshold() {
        byte[] allocation1  = new byte[4* _1MB];
    }


    /**
     * 长期存活对象进入老年代
     *
     * -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
     * Eden为8M，当分配allocation3 = new byte[4* _1MB]，触发minor GC，allocation1进入到Survivor空间中
     * 第二次allocation3 = new byte[4* _1MB]，再次触发minor GC 旧的4M对象被回收，因为MaxTenuringThreshold设置为1，allocation1进入到老年代
     *
     */
    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB /4];
        allocation2 = new byte[4* _1MB];
        allocation3 = new byte[4* _1MB];
        allocation3 = null;
        allocation3 = new byte[4* _1MB];
    }

    /**
     * 动态年龄判断
     *
     * -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
     *  allocation1,allocation1 占用空间为Survivor的一半，Minor GC时候直接进入老年代，通过动态对象年龄判断。
     *
     * Heap
     *  def new generation   total 9216K, used 4297K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     *   eden space 8192K,  52% used [0x00000000fec00000, 0x00000000ff032238, 0x00000000ff400000)
     *   from space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500310, 0x00000000ff600000)
     *   to   space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
     *  tenured generation   total 10240K, used 5723K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     */
    public static void testTenuringThreshold2() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB /4];
        allocation2 = new byte[_1MB /4];
        allocation3 = new byte[4* _1MB];
        allocation4 = new byte[4* _1MB];
        allocation4 = null;
        allocation4 = new byte[4* _1MB];
    }

    /**
     * 动态空间担保
     * -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:HandlePromotionFailure
     * jdk6之后 -XX:HandlePromotionFailure 参数作废。
     */
    public static void testHandlePromotion() {
        byte[] allocation1, allocation2, allocation3, allocation4,allocation5,allocation6,allocation7;
        allocation1 = new byte[2* _1MB];
        allocation2 = new byte[2* _1MB];
        allocation3 = new byte[2* _1MB];
        allocation1 = null;
        allocation4 = new byte[2* _1MB];
        allocation5 = new byte[2* _1MB];
        allocation6 = new byte[2* _1MB];
        allocation4 = null;
        allocation5 = null;
        allocation6 = null;
        allocation7 = new byte[2* _1MB];
    }
}
