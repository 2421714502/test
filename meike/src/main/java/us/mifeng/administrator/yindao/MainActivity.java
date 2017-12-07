package us.mifeng.administrator.yindao;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import us.mifeng.administrator.yindao.bean.Bean;
import us.mifeng.administrator.yindao.utils.JsonUtils;
import us.mifeng.administrator.yindao.utils.OkUtils;

public class MainActivity extends AppCompatActivity implements OkUtils.IDownData {
    private ImageView aihao;
    private ImageView meiye;
    private ImageView chuxue;
    private ImageView zaizhi;
    private ImageView jingying;
    private int[] ids = {R.id.shenghuo, R.id.zhuti, R.id.suxing, R.id.zhichang};
    private ImageView[] img = new ImageView[ids.length];
    private ImageView kaiqi;
    private int[] chuIds = {R.id.huazhuang, R.id.meijia, R.id.meifa, R.id.meirong, R.id.meixue};
    private ImageView[] chuImg = new ImageView[chuIds.length];
    private int[] jingIds = {R.id.yeji, R.id.jingyingguanli, R.id.caiwu, R.id.ziben, R.id.qiye, R.id.tuandui};
    private ImageView[] jingImg = new ImageView[jingIds.length];
    private OkUtils utils;
    private String path = "http://api.dameiketang.com/Appapi/select/selectImg.json";
    private ImageView meiyekaiqi;
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<String> chulist = new ArrayList<>();
    private ArrayList<String> data = new ArrayList<>();
    private ArrayList<String> jinglist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initImage();
        initAnim();
        initView();
        utils = new OkUtils(this, this);
        utils.getResponse(path);
    }

    private void initImage() {
        aihao = (ImageView) findViewById(R.id.aihao);
        meiye = (ImageView) findViewById(R.id.meiye);
        kaiqi = (ImageView) findViewById(R.id.kaiqi);
        chuxue = (ImageView) findViewById(R.id.chuxue);
        zaizhi = (ImageView) findViewById(R.id.zaizhi);
        jingying = (ImageView) findViewById(R.id.jingying);
        meiyekaiqi = (ImageView) findViewById(R.id.meiyekaiqi);
        for (int i = 0; i < ids.length; i++) {
            img[i] = (ImageView) findViewById(ids[i]);
            img[i].setOnClickListener(new MySelectClick());
        }
        chuxue.setOnClickListener(new MeiYeXuanZeClick());
        zaizhi.setOnClickListener(new MeiYeXuanZeClick());
        jingying.setOnClickListener(new MeiYeXuanZeClick());
        for (int i = 0; i < chuIds.length; i++) {
            chuImg[i] = (ImageView) findViewById(chuIds[i]);
            chuImg[i].setOnClickListener(new chuXueSelectClick());
        }
        for (int i = 0; i < jingIds.length; i++) {
            jingImg[i] = (ImageView) findViewById(jingIds[i]);
            jingImg[i].setOnClickListener(new jingYingSelectClick());
        }
        kaiqi.setOnClickListener(new MeiLiClick());
        meiyekaiqi.setOnClickListener(new MeiLiClick());
    }

    @Override
    public void onResult(String json) {
        Bean bean = new JsonUtils().getData(json);
        String zhichangid = bean.getT().get职场经营().getId();
        String shenghuoid = bean.getT().get生活休闲().getId();
        String zhutiid = bean.getT().get主题活动().getId();
        String suxingid = bean.getT().get塑形养生().getId();
        list.add(shenghuoid);
        list.add(zhutiid);
        list.add(suxingid);
        list.add(zhichangid);
        String huazhuang = bean.getT().getOBC化妆().getId();
        String meijia = bean.getT().getOBC美甲().getId();
        String meifa = bean.getT().getOBC美发().getId();
        String meirong = bean.getT().getOBC美容().getId();
        String meixue = bean.getT().getOBC双线班().getId();
        chulist.add(huazhuang);
        chulist.add(meijia);
        chulist.add(meifa);
        chulist.add(meirong);
        chulist.add(meixue);
        jinglist.add(bean.getT().get业绩倍增().getId());
        jinglist.add(bean.getT().get经营管理().getId());

    }

    class MeiYeXuanZeClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.chuxue:
                    chuXueClick();
                    break;
                case R.id.zaizhi:
                    zaiZhiClick();
                    break;
                case R.id.jingying:
                    jingYingClick();
                    break;
            }
        }
    }

    private void zaiZhiClick() {
        isXuanZeVisible(View.GONE, new ImageView[]{chuxue, jingying, meiye});
        ObjectAnimator ob = ObjectAnimator.ofFloat(zaizhi, "translationY", 180, 0);
        ob.setDuration(2000);
        ob.start();
        xuanZe();
    }

    private void jingYingClick() {
        isXuanZeVisible(View.GONE, new ImageView[]{zaizhi, chuxue, meiye});
        ObjectAnimator oa = ObjectAnimator.ofFloat(jingying, "translationX", 150, 0);
        ObjectAnimator ob = ObjectAnimator.ofFloat(jingying, "translationY", 180, 0);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(2000);
        set.play(oa).with(ob);
        set.start();
        isXuanZeVisible(View.VISIBLE, jingImg);
        AnimatorSet as1 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.huazhuangtranslation);
        AnimatorSet as2 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.meijiatranslation);
        AnimatorSet as3 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.meifatranslation);
        AnimatorSet as4 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.meirongtranslation);
        AnimatorSet as5 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.meixuetranslation);
        AnimatorSet as6 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.tuanduitranslation);
        as1.setTarget(jingImg[0]);
        as2.setTarget(jingImg[1]);
        as3.setTarget(jingImg[2]);
        as4.setTarget(jingImg[3]);
        as5.setTarget(jingImg[4]);
        as6.setTarget(jingImg[5]);
        as1.start();
        as2.start();
        as3.start();
        as4.start();
        as5.start();
        as6.start();
        isFlag1(false);
    }

    class MySelectClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int i = 0;
            switch (v.getId()) {
                case R.id.shenghuo:
                    i = 0;
                    break;
                case R.id.suxing:
                    i = 2;
                    break;
                case R.id.zhichang:
                    i = 3;
                    break;
                case R.id.zhuti:
                    i = 1;
                    break;
            }
            if (img[i].isSelected()) {
                img[i].setSelected(false);
                String id = list.get(i);
                data.remove(id);
            } else {
                img[i].setSelected(true);
                String id = list.get(i);
                data.add(id);
            }
        }
    }

    class chuXueSelectClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int i = 0;
            switch (v.getId()) {
                case R.id.huazhuang:
                    i = 0;
                    break;
                case R.id.meijia:
                    i = 1;
                    break;
                case R.id.meifa:
                    i = 2;
                    break;
                case R.id.meirong:
                    i = 3;
                    break;
                case R.id.meixue:
                    i = 4;
                    break;
            }
            if (chuImg[i].isSelected()) {
                chuImg[i].setSelected(false);
                String id = chulist.get(i);
                data.remove(id);
            } else {
                chuImg[i].setSelected(true);
                String id = chulist.get(i);
                data.add(id);
            }
        }
    }

    class jingYingSelectClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int i = 0;
            switch (v.getId()) {
                case R.id.yeji:
                    i = 0;
                    break;
                case R.id.jingyingguanli:
                    i = 1;
                    break;
                case R.id.caiwu:
                    i = 2;
                    break;
                case R.id.ziben:
                    i = 3;
                    break;
                case R.id.qiye:
                    i = 4;
                    break;
                case R.id.tuandui:
                    i = 5;
                    break;
            }
            if (jingImg[i].isSelected()) {
                jingImg[i].setSelected(false);
                String id = jinglist.get(i);
                data.remove(id);
            } else {
                jingImg[i].setSelected(true);
                String id = jinglist.get(i);
                data.add(id);
            }
        }
    }

    class MeiLiClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.kaiqi:
                    for (int i = 0; i < data.size(); i++) {
                        Log.i("tag", "====kaiqi====" + data.get(i));
                    }
                    break;
                case R.id.meiyekaiqi:
                    for (int i = 0; i < data.size(); i++) {
                        Log.i("tag", "====meiye====" + data.get(i));
                    }
                    break;
            }
        }
    }

    private void initAnim() {
        ObjectAnimator animator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.translation);
        animator.setTarget(aihao);
        animator.start();
        ObjectAnimator animator1 = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.translation1);
        animator1.setTarget(meiye);
        animator1.start();
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(meiye, "alpha", 0f, 1f);
        animator2.start();
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(aihao, "alpha", 0f, 1f);
        animator3.start();
    }

    public void isFlag(boolean flag) {
        aihao.setEnabled(flag);
        meiye.setEnabled(flag);
    }

    private void isFlag1(boolean flag) {
        chuxue.setEnabled(flag);
        zaizhi.setEnabled(flag);
        jingying.setEnabled(flag);
    }

    private void aiHaoListener() {
        ObjectAnimator oa = ObjectAnimator.ofFloat(aihao, "translationX", -123, 0);
        ObjectAnimator ob = ObjectAnimator.ofFloat(meiye, "translationX", 123, 0);
        ObjectAnimator oc = ObjectAnimator.ofFloat(meiye, "alpha", 1f, 0f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(2000);
        set.play(ob).with(oc).with(oa);
        set.start();
        isFlag(false);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isVisible(View.VISIBLE);
                ObjectAnimator aa = (ObjectAnimator) AnimatorInflater.loadAnimator(MainActivity.this, R.animator.alpha);
                ObjectAnimator ba = ObjectAnimator.ofFloat(img[3], "translationX", 0f, -150f);
                ObjectAnimator ca = ObjectAnimator.ofFloat(img[2], "translationX", 0f, 150f);
                ObjectAnimator da = ObjectAnimator.ofFloat(img[0], "translationX", 0f, -100f);
                ObjectAnimator da1 = ObjectAnimator.ofFloat(img[0], "translationY", 0f, -200f);
                ObjectAnimator ea = ObjectAnimator.ofFloat(img[1], "translationX", 0f, 100f);
                ObjectAnimator ea1 = ObjectAnimator.ofFloat(img[1], "translationY", 0f, -200f);
                AnimatorSet set1 = new AnimatorSet();
                aa.setTarget(img[3]);
                aa.setTarget(img[2]);
                aa.setTarget(img[0]);
                aa.setTarget(img[1]);
                aa.setTarget(kaiqi);
                aa.start();
                set1.play(ba).with(ca).with(da).with(da1).with(ea).with(ea1);
                set1.setDuration(2000);
                set1.start();
                super.onAnimationEnd(animation);
            }
        });
    }

    private void isVisible(int a) {
        img[3].setVisibility(a);
        img[2].setVisibility(a);
        img[0].setVisibility(a);
        img[1].setVisibility(a);
        kaiqi.setVisibility(a);
    }

    private void isVisibleMeiYe(int a) {
        chuxue.setVisibility(a);
        zaizhi.setVisibility(a);
        jingying.setVisibility(a);
        meiyekaiqi.setVisibility(a);
    }

    private void meiYeListener() {
        ObjectAnimator oa = ObjectAnimator.ofFloat(aihao, "translationX", -123, 0);
        ObjectAnimator ob = ObjectAnimator.ofFloat(meiye, "translationX", 123, 0);
        ObjectAnimator oc = ObjectAnimator.ofFloat(aihao, "alpha", 1f, 0f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(2000);
        set.play(oa).with(oc).with(ob);
        set.start();
        isFlag(false);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isVisibleMeiYe(View.VISIBLE);
                ObjectAnimator aa = (ObjectAnimator) AnimatorInflater.loadAnimator(MainActivity.this, R.animator.alpha);
                aa.setTarget(chuxue);
                aa.setTarget(zaizhi);
                aa.setTarget(jingying);
                aa.setTarget(meiyekaiqi);
                aa.start();
                ObjectAnimator ab = ObjectAnimator.ofFloat(chuxue, "translationX", 0, -150);
                ObjectAnimator ac = ObjectAnimator.ofFloat(chuxue, "translationY", 0, 180);
                ObjectAnimator ad = ObjectAnimator.ofFloat(zaizhi, "translationY", 0, 180);
                ObjectAnimator ae = ObjectAnimator.ofFloat(jingying, "translationX", 0, 150);
                ObjectAnimator af = ObjectAnimator.ofFloat(jingying, "translationY", 0, 180);
                AnimatorSet set1 = new AnimatorSet();
                set1.setDuration(2000);
                set1.play(ab).with(ac).with(ad).with(ae).with(af);
                set1.start();
                super.onAnimationEnd(animation);
            }
        });
    }

    private void initView() {
        aihao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aiHaoListener();
            }
        });
        meiye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meiYeListener();
            }
        });
    }

    public void reStart(View view) {
        initAnim();
        isFlag(true);
        isFlag1(true);
        isVisible(View.GONE);
        isVisibleMeiYe(View.GONE);
        isXuanZeVisible(View.VISIBLE, new ImageView[]{meiye});
        isXuanZeVisible(View.GONE, chuImg);
        isXuanZeVisible(View.GONE, jingImg);
        for (int i = 0; i < img.length; i++) {
            img[i].setSelected(false);
        }
        for (int i = 0; i < chuImg.length; i++) {
            chuImg[i].setSelected(false);
        }
        for (int i = 0; i < jingImg.length; i++) {
            jingImg[i].setSelected(false);
        }
        data.clear();
    }

    private void isXuanZeVisible(int a, ImageView[] imgs) {
        for (int i = 0; i < imgs.length; i++) {
            imgs[i].setVisibility(a);
        }
    }

    private void chuXueClick() {
        isXuanZeVisible(View.GONE, new ImageView[]{zaizhi, jingying, meiye});
        ObjectAnimator oa = ObjectAnimator.ofFloat(chuxue, "translationX", -150, 0);
        ObjectAnimator ob = ObjectAnimator.ofFloat(chuxue, "translationY", 180, 0);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(2000);
        set.play(oa).with(ob);
        set.start();
        xuanZe();
    }

    private void xuanZe() {
        isXuanZeVisible(View.VISIBLE, chuImg);
        AnimatorSet as1 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.huazhuangtranslation);
        AnimatorSet as2 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.meijiatranslation);
        AnimatorSet as3 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.meifatranslation);
        AnimatorSet as4 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.meirongtranslation);
        AnimatorSet as5 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.meixuetranslation);
        as1.setTarget(chuImg[0]);
        as2.setTarget(chuImg[1]);
        as3.setTarget(chuImg[2]);
        as4.setTarget(chuImg[3]);
        as5.setTarget(chuImg[4]);
        as1.start();
        as2.start();
        as3.start();
        as4.start();
        as5.start();
        isFlag1(false);
    }
}
