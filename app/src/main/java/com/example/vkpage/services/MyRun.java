package com.example.vkpage.services;

import java.util.concurrent.TimeUnit;

public class MyRun implements Runnable {

    int time;
    int startId;

    public MyRun(int time, int startId){
        this.time=time;
        this.startId=startId;
    }
    @Override
    public void run(){
        try{
            TimeUnit.SECONDS.sleep(time);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
}