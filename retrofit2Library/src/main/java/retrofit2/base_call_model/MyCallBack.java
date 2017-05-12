package retrofit2.base_call_model;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jrm on 2017-4-21.
 * 这里的回调主要是针对 返回的json的数据格式完全死统一的形式 才可以用；
 * 自定义回调接口，实现Retrofit的回调 CallBack
 */

public  abstract class MyCallBack <T extends BaseCallModel> implements Callback<T>{

    Handler retroHandle;
    public MyCallBack(){
         retroHandle =  new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if(t instanceof SocketTimeoutException){
            //
        }else if(t instanceof ConnectException){
            //
        }else if(t instanceof RuntimeException){
            //
        }
       onFailure(t.getMessage());
    }

    @Override
    public void onResponse(final Call<T> call, final Response<T> response) {
        if (response.raw().code() == 200){ //服務器正常
            retroHandle.post(new Runnable() {
                @Override
                public void run() {
                    onSucess(response);
                    if (call != null){
                        call.cancel();
                    }
                }
            });
        }else {
            onFailure(response.body().msg);
        }
    }

    public  abstract  void onSucess(Response<T> response);
    public  abstract void onFailure(String message);
}
