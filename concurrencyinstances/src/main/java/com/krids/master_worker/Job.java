package com.krids.master_worker;


import java.io.FileWriter;
import java.util.concurrent.Callable;

/**
 * do some thing, and to show the process
 */
public class Job implements Callable<String> {
    /**
     * to write some message to show
     */
    private String message;
    private String fileName;

    Job() {
    }

    public Job(String message, String fileName) {
        this.message = message;
        this.fileName = fileName;
    }

    /**
     * write message to a file distributed where worker should exec
     *
     * @return
     * @throws Exception
     */
    @Override
    public String call() throws Exception {
        FileWriter fileWriter = new FileWriter(fileName);
        try{
            fileWriter.write(message);
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return fileName + "failed";
        } finally {
            fileWriter.close();
        }

        return fileName + "OK";
    }
}
