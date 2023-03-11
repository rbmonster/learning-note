package com.learning.offer;

/**
 * <pre>
 * @Description: 约瑟夫环
 *   圆圈中剩下的数
 * </pre>
 *
 * @version v1.0
 * @ClassName: LastRemaining_Solution
 * @Author: 86159
 * @Date: 2020/3/24 20:55
 */
public class LastRemaining {
    public static void main(String[] args) {
        int last = new LastRemaining().lastRemain(5, 3);
        System.out.println("last remain child num is :"+ last);
    }

    public int lastRemain(int n, int m) {
        if(n < 0 || m<0) {
            return  -1;
        }
        if (n == 1) {
            return 0;
        }
        return (lastRemain(n-1 , m) +m) % n;
    }

}
