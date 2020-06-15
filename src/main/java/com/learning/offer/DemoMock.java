package com.learning.offer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * @Description: TODO(一句话描述该类的功能)
 * </pre>
 *
 * @version v1.0
 * @ClassName: DemoTest
 * @Author: 86159
 * @Date: 2020/3/25 22:21
 */
public class DemoMock {
    public List<String> doTest() {
        System.out.println("this is do test");
        List<String> list = new ArrayList<>();
        list.add("123123");
        list.add("1212");
        test1(list.get(0));
        return list;
    }

    private void test1(String str) {
        System.out.println(str);
        throw  new RuntimeException("error");
    }

    public static void main(String[] args) {
//        Map<String,String> map = new HashMap<String,String>();
//        map.put("123","1221");
//        map.put("2","1221");
//        map.put("12","1221");
//        map.put("123","1221");
//        map.put("1","1221");
//        System.out.println(map.values());
        int a = new DemoMock().minNumberInRotateArray(new int[]{3, 4, 5, 1, 2});
        System.out.println(a);
    }

    public int minNumberInRotateArray(int [] array) {
        if(array==null || array.length ==0) {
            return 0;
        }
        int temp = array[array.length-1];
        for(int i = array.length-1 ; i >= 0; i--){
            if(array[i]> temp){
                break;
            }
        }
        return temp;
    }
}
