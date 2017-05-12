package retrofitService;

import android.os.Handler;
import android.os.Looper;

import api.GithubApi;
import retrofit2.Retrofit2Client;
import retrofitService.bean.Folder;

/**
 * Created by jrm on 2017-4-5.
 * 对 retrofit的 进一步封装
 */

public class RetrofitManager extends Retrofit2Client{

    private Handler retroHandle;

    private RetrofitManager() {
        super();
        this.retroHandle = new Handler(Looper.getMainLooper());
    }
    private static final class Factory {
        private static final RetrofitManager instance = new RetrofitManager();
    }
    public static RetrofitManager getInstance() {
        return Factory.instance;
    }

    /**
     * 添加参数的方式
     */
    public void postAppInfoParams(){
        RetrofitService.getInstance();
    }
}
