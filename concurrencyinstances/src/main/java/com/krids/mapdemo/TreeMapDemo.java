package com.krids.mapdemo;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ck on 2018/8/16.
 */
public class TreeMapDemo {
    public static void main(String[] args) {
        TreeMap<Object,Object> treeMap = new TreeMap<Object, Object>();

        treeMap.put("1",1);
        treeMap.put("10",10);
        treeMap.put("11",11);
        treeMap.put("5",5);
        treeMap.put("4",4);
        treeMap.put("9",9);
        // 若存在相同的key，则不再添加
       // treeMap.putIfAbsent("4",44);

        for(Object key : treeMap.keySet()) {
            System.out.print(treeMap.get(key) + " ");
        }



//        Map<String, String> treeMap1 = new TreeMap<String, String>();
//        treeMap.put("a", "1");
//        treeMap.put("b", "2");
//        treeMap.put("c", "3");
//        treeMap.put("d", "4");
//
//        System.out.print("TreeMap:");
//        for(String key : treeMap.keySet()) {
//            System.out.print(treeMap.get(key) + " ");
//        }

    }
}
