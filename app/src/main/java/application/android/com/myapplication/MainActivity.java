package application.android.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import utils.HttpUtils;
import utils.JavaBean;
import utils.JsonUtils;
import utils.MyAdapter;

public class MainActivity extends AppCompatActivity implements HttpUtils.Down {

    private PullToRefreshListView pListView;
    private int index = 1;
    private String path = "http://192.168.190.188/Goods/app/item/list.json?curPage=" + index + "";
    private ArrayList<JavaBean.DataBean.ListBean> list = new ArrayList<>();
    private HttpUtils utils;
    private MyAdapter adapter;
    private List<JavaBean.DataBean.ListBean> list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        utils = new HttpUtils(this);
        initView();
    }

    private void initView() {
        pListView = (PullToRefreshListView) findViewById(R.id.pListView);
        pListView.setMode(PullToRefreshBase.Mode.BOTH);
        pListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                index++;
                utils.getRequest(path);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                index++;
                utils.getRequest(path);
            }
        });
        adapter = new MyAdapter(this, list);
        pListView.setAdapter(adapter);
    }

    @Override
    public void download(String json) {
        pListView.onRefreshComplete();
        if (json == null) {
            return;
        }
        Log.i("tag", json);

        JavaBean bean = new JsonUtils().getJavaBean(json);
        if (bean.getStatus().equals("200") && bean.getInfo().equals("成功")) {
            JavaBean.DataBean db = bean.getData();
            list2 = db.getList();
            list.clear();
        }
        if (list2 != null) {
            list.addAll(list2);
            adapter.notifyDataSetChanged();
        }
    }
}
