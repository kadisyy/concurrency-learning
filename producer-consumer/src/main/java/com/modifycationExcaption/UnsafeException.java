package com.modifycationExcaption;


import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class UnsafeException {
    public Collection<String> unsafe(Vector<String> list){
        for(String tmp: list){
            if("51".equals(tmp)){
                list.remove(tmp);
            }
        }
        return list;
    }

    public Collection<String> safe(Vector<String> list){
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            String tmp = iterator.next();
            if("31".equals(tmp)){
                iterator.remove();
            }
        }
        return list;
    }

    public static void main(String[] args) {
        Vector vector = new Vector<>();
        vector.add("11");
        vector.add("21");
        vector.add("31");
        vector.add("41");
        vector.add("51");

        UnsafeException unsafeException = new UnsafeException();
        Collection<String> res = unsafeException.safe(vector);
        System.out.println(res.toString());
    }
}
