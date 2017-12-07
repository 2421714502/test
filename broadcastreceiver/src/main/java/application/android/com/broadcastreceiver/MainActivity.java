package application.android.com.broadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MyBoradcast mb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*mb = new MyBoradcast();
        IntentFilter filter=new IntentFilter();
        filter.addAction("us.buba");
        registerReceiver(mb,filter);*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        //动态注册广播
        mb = new MyBoradcast();
        IntentFilter filter = new IntentFilter();
        filter.addAction("us.buba");
        registerReceiver(mb, filter);
    }

    public void send(View view) {
        Intent intent = new Intent();
        intent.putExtra("str", "妈卖批");
        intent.setAction("us.buba.cc");
        //发送广播
        sendBroadcast(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mb != null) {
            //unregisterReceiver(mb);
        }
    }
}
