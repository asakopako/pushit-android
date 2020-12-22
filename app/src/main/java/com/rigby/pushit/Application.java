package com.rigby.pushit;

public class Application extends android.app.Application {

    public static Application application;


    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

}