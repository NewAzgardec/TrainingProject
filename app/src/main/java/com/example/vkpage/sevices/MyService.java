package com.example.vkpage.sevices;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    ExecutorService executorService;

    public MyService(){
        super.onCreate();
        executorService= Executors.newFixedThreadPool(1);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
    }
    @Override
    public void onCreate(){
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        int time = intent.getIntExtra("time",1);
        MyRun myRun = new MyRun(time, startId);
        executorService.execute(myRun);
        return super.onStartCommand(intent,flags,startId);
    }
    @Override
    public void onRebind(Intent intent){
        super.onRebind(intent);
    }
    @Override
    public boolean onUnbind(Intent intent){
        return super.onUnbind(intent);
    }
    @Override
    public IBinder onBind(Intent intent){
        return new Binder();
    }
    private void executeTask(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<5; i++){
                    try{
                        TimeUnit.SECONDS.sleep(2);
                    }catch (InterruptedException ex){
                        ex.printStackTrace();
                    }
                }
                stopSelf();
            }
        }).start();
    }
}
