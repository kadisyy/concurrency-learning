package com.krids.master_worker;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

//单利模式
public class CommonUtils {
    public volatile static ConcurrentLinkedQueue<Job> INPUT_TASK_QUEUE = null;

    public volatile static ConcurrentHashMap<String, String> EXEC_RESULTS = null;

    public static ConcurrentLinkedQueue<Job> getInstanceQueue() {
        if(INPUT_TASK_QUEUE == null) {
            INPUT_TASK_QUEUE = new ConcurrentLinkedQueue<Job>();
        }
        return INPUT_TASK_QUEUE;
    }


    public static ConcurrentHashMap<String, String> getInstanceMap() {
        if(EXEC_RESULTS == null) {
            EXEC_RESULTS = new ConcurrentHashMap<String, String>();
        }
        return EXEC_RESULTS;
    }
}
