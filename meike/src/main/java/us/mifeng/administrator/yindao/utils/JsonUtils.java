package us.mifeng.administrator.yindao.utils;

import com.google.gson.Gson;


import us.mifeng.administrator.yindao.bean.Bean;

/**
 * Created by Administrator on 2017/11/23.
 */

public class JsonUtils {
    public Bean getData(String json) {
        Gson gson = new Gson();
        Bean bean = gson.fromJson(json, Bean.class);
        return bean;
    }
}