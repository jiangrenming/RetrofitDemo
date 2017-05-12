package retrofitService.bean;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import utils.Md5Utils;

/**
 * Created by jrm on 2017-4-21.
 * 参数的转换
 */

public class CoverParams {

    /**
     * 对所给的参数进行加密处理
     * @param params
     */
    public static Map<String,String> getParams(Map<String ,String > params){
        Map<String ,String> map = new HashMap<>();
        ArrayList<String> pair = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if (params == null || params.size() == 0){
            map.put("sign_parm", "sign");
            sb.append(map.get("sign_parm"));
        }else {
            Set<String> keys = params.keySet();
            for (String key : keys) {
                if (params.get(key) == null) continue;
                String value = params.get(key).toString();
                if (TextUtils.isEmpty(value)) {
                    continue;
                }
                map.put(key,value);
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
        sb = null;
        return  map;
    }
}
