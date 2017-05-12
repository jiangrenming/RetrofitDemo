package application;

import android.app.Application;

import retrofitService.RetrofitService;
import utils.ContextUtils;

/**
 * Created by Administrator on 2017-4-5.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitService.getInstance();
    }
}
