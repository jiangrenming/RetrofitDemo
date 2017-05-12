package com.example.jrm.retrofitdemo;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.base_call_model.BaseCallModel;
import retrofit2.base_call_model.MyCallBack;
import retrofitService.RetrofitService;
import retrofitService.bean.CoverParams;
import retrofitService.bean.Folder;
import retrofitService.bean.FolderLodingInfo;
import utils.ConvertParams;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.txt);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> paramsThree = ConvertParams.getInstatnce().getParamsThree("floderId", String.valueOf(1),
                        "pageSize", String.valueOf(1),
                        "pageNumber", String.valueOf(32));
                RetrofitService.getInstance().githubApi.createFolderMapTwo(paramsThree).enqueue(new MyCallBack<BaseCallModel<List<FolderLodingInfo>>>() {
                    @Override
                    public void onSucess(Response<BaseCallModel<List<FolderLodingInfo>>> response) {
                        Log.i("TAG",response.toString());
                        List<FolderLodingInfo> data = response.body().data;
                    }

                    @Override
                    public void onFailure(String message) {
                        Log.i("TAGA",message);
                    }
                });
            }
        });
    }
}
