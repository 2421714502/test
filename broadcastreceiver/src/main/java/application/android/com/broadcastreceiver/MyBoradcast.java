package application.android.com.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/12/6.
 */

public class MyBoradcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String str = intent.getStringExtra("str");
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }
}
