package retrofitService;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import api.GithubApi;
import retrofit2.*;
import retrofitService.bean.CoverParams;
import retrofitService.bean.Folder;

/**
 * Created by jrm on 2017-3-29.
 * 初始化retrofit的入口处,如果需要初始化多个不同的API的话，可以在这里进行自行添加
 */

public class  RetrofitService extends Retrofit2Client {

   public  static RetrofitService service;
   public  static GithubApi githubApi;
    private Handler retroHandle;

    public  static  RetrofitService getInstance(){
        if (service == null){
            service = new RetrofitService();
        }
        return  service;
    }

    public RetrofitService(){
        githubApi =  retrofitBuilder.baseUrl(GithubApi.BASE_URL).build().create(GithubApi.class);
        this.retroHandle = new Handler(Looper.getMainLooper());
    }


    /**
     * 以下的方法及接口主要针对如下情况使用：
     * 当返回的数据结构并不是按照统一格式时，需要使用一下的方法来代替之前的方法，若使用下面的方法，需要从最外层开始创建数据模型
     * 请谨慎使用~
     *
     */
    public interface MyCallBack{
       <T> void onSucess(T object);
        void onFailure(String error);
    }

    /**
     * 直接添加参数的方式
     */
    public void postAppInfoParams(final MyCallBack myCallBack){
        Call<Folder> folder = githubApi.createFolder(1, 1, 32);
        folder.enqueue(new Callback<Folder>() {
            @Override
            public void onResponse(final Call<Folder> call, final Response<Folder> response) {
                Log.i("tag---",response.body().toString());
                retroHandle.post(new Runnable() {
                    @Override
                    public void run() {
                        if (myCallBack != null){
                            myCallBack.onSucess(response.body());
                            if (call != null){
                                call.cancel();
                            }
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<Folder> call, Throwable t) {
                String message = t.getMessage();
                if (myCallBack != null) {
                    myCallBack.onFailure(message);
                }
            }
        });
    }

    /**
     * map的方式添加参数
     */
    public void postAppInfoMapParams(final MyCallBack myCallBack,int folderId,int pageSize,int pageNumber){
        Map<String ,String> options = new HashMap<>();
        options.put("floderId",String.valueOf(folderId));
        options.put("pageSize",String.valueOf(pageSize));
        options.put("pageNumber",String.valueOf(pageNumber));
        Map<String, String> params = CoverParams.getParams(options);
        githubApi.createFolderMap(params).enqueue(new Callback<Folder>() {
            @Override
            public void onResponse(final Call<Folder> call, final Response<Folder> response) {
                Log.i("tag---",response.body().toString());
                retroHandle.post(new Runnable() {
                    @Override
                    public void run() {
                        if (myCallBack != null){
                            myCallBack.onSucess(response.body());
                            if (call != null){
                                call.cancel();
                            }
                        }
                    }
                });
            }
            @Override
            public void onFailure(Call<Folder> call, Throwable t) {
                String message = t.getMessage();
                Log.i("tag---",message);
                if (myCallBack != null) {
                    myCallBack.onFailure(message);
                }
            }
        });
    }
}
