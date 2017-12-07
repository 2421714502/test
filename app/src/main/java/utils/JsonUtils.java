package utils;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2017/11/27.
 */

public class JsonUtils {
    public JavaBean getJavaBean(String json) {
        Gson gson = new Gson();
        JavaBean javaBean = gson.fromJson(json, JavaBean.class);
        return javaBean;
    }
}
