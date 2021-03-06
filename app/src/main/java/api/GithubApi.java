package api;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.base_call_model.BaseCallModel;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofitService.bean.Folder;
import retrofitService.bean.FolderLodingInfo;

/**
 * Created by jrm on 2017-3-29.
 * 存放接口的地方
 */

public interface GithubApi {
   // http://zm.youmeng.com/Api/Folder/getPageFolderSoft
    public static final String  BASE_URL ="http://zm.youmeng.com/Api/";

    //获取虚拟文件夹  folderId= 1或3
    @FormUrlEncoded
    @POST("Folder/getPageFolderSoft")
    Call<Folder> createFolder(@Field("floderId") int floderId, @Field("pageSize") int pageSize, @Field("pageNumber")  int pageNumber);


   @FormUrlEncoded
   @POST("Folder/getPageFolderSoft")
   Call<Folder> createFolderMap(@FieldMap Map<String,String> value);

    @FormUrlEncoded
    @POST("Folder/getPageFolderSoft")
    Call<BaseCallModel<List<FolderLodingInfo>>> createFolderMapTwo(@FieldMap Map<String,String> value);

}
