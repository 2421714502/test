package us.mifeng.administrator.yindao.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/11/23.
 */

public class OkUtils {
    private Context context;
    private OkHttpClient instance;
    private IDownData id;
    private final Handler handler;

    public OkUtils(Context context, IDownData id) {
        this.context = context;
        this.id = id;
        handler = new Handler();
    }

    public boolean isOk() {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info.getState().equals(NetworkInfo.State.CONNECTED)) {
            return true;
        }
        return false;
    }

    public OkHttpClient getClient() {
        if (instance == null) {
            synchronized (this) {
                if (instance == null) {
                    instance = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).connectTimeout(5, TimeUnit.SECONDS).build();
                }
            }
        }
        return instance;
    }

    public void getResponse(String path) {
        if (!isOk()) {
            return;
        }
        OkHttpClient client = getClient();
        Request request = new Request.Builder().url(path).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String s = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        id.onResult(s);
                    }
                });
            }
        });
    }

    public interface IDownData {
        void onResult(String json);
    }
}
