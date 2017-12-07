package utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import application.android.com.myapplication.R;

/**
 * Created by Administrator on 2017/11/27.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<JavaBean.DataBean.ListBean> list;

    public MyAdapter(Context context, ArrayList<JavaBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public JavaBean.DataBean.ListBean getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHodler vh = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, null);
            vh = new ViewHodler(view);
            view.setTag(vh);
        } else {
            vh = (ViewHodler) view.getTag();
        }
        JavaBean.DataBean.ListBean lb = getItem(i);

        vh.name.setText(lb.getContact());
        vh.price.setText(lb.getPrice());
        Picasso.with(context).load("http://192.168.190.188/Goods/app/item/list.json/" + lb.getHead()).into(vh.imgHead);
        Picasso.with(context).load("http://192.168.190.188/Goods/app/item/list.json/" + lb.getImage()).into(vh.imgBody);
        return view;
    }

    class ViewHodler {
        TextView name, price;
        ImageView imgHead, imgBody;

        public ViewHodler(View view) {
            name = (TextView) view.findViewById(R.id.name);
            price = (TextView) view.findViewById(R.id.price);
            imgHead = (ImageView) view.findViewById(R.id.imgHead);
            imgBody = (ImageView) view.findViewById(R.id.imgBody);
        }
    }
}
