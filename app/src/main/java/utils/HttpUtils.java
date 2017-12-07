package utils;

import android.os.Handler;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/11/27.
 */

public class HttpUtils {
    private Handler handler;
    private Down down;

    public HttpUtils(Down down) {
        this.down = down;
        handler = new Handler();
    }

    public void getRequest(String path) {
        OkHttpClient cilent = new OkHttpClient();
        Request request = new Request.Builder()
                .url(path).build();
        Call call = cilent.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        down.download(str);
                    }
                });
            }
        });
    }

    public interface Down {
        void download(String json);
    }
}
