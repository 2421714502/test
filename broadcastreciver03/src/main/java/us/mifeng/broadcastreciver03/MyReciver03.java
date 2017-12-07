package us.mifeng.broadcastreciver03;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by 黑夜之火 on 2017/12/6.
 */

public class MyReciver03 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String text = intent.getStringExtra("text");
        Toast.makeText(context, "我是03Reciver接收到的" + text, Toast.LENGTH_SHORT).show();
        //拦截事件
        //abortBroadcast();
        setResultData("我是改变之后的数据");
    }
}
