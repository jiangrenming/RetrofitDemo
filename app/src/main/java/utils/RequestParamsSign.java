package utils;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import api.GithubApi;

/**
 * Created by Administrator on 2017-3-31.
 * post请求的参数签名:签名 youmeng.com 网络路径的参数
 * 主要是针对请求数据之前对参数的一个加密等的处理操作~
 */

public class RequestParamsSign {

    private static final String YOU_MENG_TAG ="youmeng.com";

    private static  RequestParamsSign sign;

    public static RequestParamsSign getParams(Bundle values){
        if (sign == null){
            sign = new RequestParamsSign(values);
        }
        return  sign;
    }
    public RequestParamsSign(Bundle values){
        if (GithubApi.BASE_URL.contains(YOU_MENG_TAG)){
            initParamsYouMeng(values);
        }else {
            initParams(values);
        }
    }

    private void initParams(Bundle values) {
        list = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        Map<String,String> map = null;
        if (values == null || values.size() == 0) {
            map = new HashMap<>();
            map.put("sign_parm", "sign");
            list.add(map);
            builder.append(map.get("sign_parm"));
        } else {
            Set<String> keys = values.keySet();
            for (String key : keys) {
                if (values.get(key) == null) continue;
                String value = values.get(key).toString();
                if (TextUtils.isEmpty(value)) {
                    continue;
                }
                map = new HashMap<>();
                map.put(key,value);
                list.add(map);
                builder.append(value);
            }

        }
        String sign = builder.toString();
        String code = Md5Utils.strCodeZm(Md5Utils.getMD5(sign));
        code = code.substring(0, code.length() - 1);
        map.put("sign",code);
        list.add(map);
        builder = null;
    }

    // 用于确保和服务器加密的顺序一致
    private List<Map<String,String>> list ;
    private void initParamsYouMeng(Bundle values) {
        ArrayList<String> pair = new ArrayList<>();
        list = new ArrayList<>();
        Map<String,String> map = null;
        StringBuilder sb = new StringBuilder();
        if (values == null || values.size() == 0){
            map = new HashMap<>();
            map.put("sign_parm", "sign");
            list.add(map);
            sb.append(map.get("sign_parm"));
        }else {
            Set<String> keys = values.keySet();
            for (String key : keys) {
                if (values.get(key) == null) continue;
                String value = values.get(key).toString();
                if (TextUtils.isEmpty(value)) {
                    continue;
                }
                map = new HashMap<>();
                map.put(key,value);
                list.add(map);
                pair.add(key + "=" + value);
            }
            Collections.sort(pair);
            for (String temp : pair) {
                sb.append(temp);
            }
        }
        String sign = sb.toString();
        String code = Md5Utils.strCodeRt(sign);
        map.put("sign",code);
        list.add(map);
        sb = null;
    }
}
