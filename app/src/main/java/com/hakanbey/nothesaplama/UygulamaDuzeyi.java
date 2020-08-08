package com.hakanbey.nothesaplama;

import android.app.Application;

public class UygulamaDuzeyi extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MainActivity.vb.getDb().setPersistenceEnabled(true);
    }

}
