package com.example.two;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageview);
    }

    public void translate1(View view) {
        TranslateAnimation animation = new TranslateAnimation(0, 200, 0, 200);
        animation.setDuration(2000);
        animation.setRepeatCount(2);
        animation.setRepeatMode(Animation.REVERSE);
        imageView.startAnimation(animation);
    }

    public void rotate1(View view) {
        RotateAnimation animation = new RotateAnimation(
                0, 360.0f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
        );
        animation.setDuration(2000);
        animation.setRepeatCount(2);
        animation.setRepeatMode(Animation.RESTART);
        imageView.startAnimation(animation);
    }

    public void scale1(View view) {
        ScaleAnimation animation = new ScaleAnimation(2.0f, 0.5f, 2.0f, 0.5f);
        animation.setDuration(2000);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(2);
        imageView.startAnimation(animation);
    }

    public void alpha1(View view) {
        AlphaAnimation animation = new AlphaAnimation(0, 1.0f);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(2);
        animation.setDuration(2000);
        imageView.startAnimation(animation);
    }

    public void zong1(View view) {
        AnimationSet set = (AnimationSet)
                AnimationUtils.loadAnimation(this, R.anim.zong);
        imageView.startAnimation(set);
    }

    public void translate2(View view) {
        ObjectAnimator animatorX =
                ObjectAnimator.ofFloat(imageView,
                        "translationY", 0.1f, 500f, 300f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(imageView, "translationX", 0.1f, 400f, 200f);
        AnimatorSet set = new AnimatorSet();
        set.play(animatorX).with(animatorY);
        set.setDuration(4000);
        set.start();
    }

    public void rotate2(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(
                imageView, "rotation", 0, 360, 180, 90
        );
        animator.setDuration(3000);
        animator.setRepeatCount(2);
        animator.start();
    }

    public void scale2(View view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(
                imageView, "scaleX", 1.0f, 2.0f, 0.5f
        );
        scaleX.setRepeatCount(2);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(
                imageView, "scaleY", 1.0f, 2.0f, 0.5f
        );
        scaleY.setRepeatCount(2);
        AnimatorSet set = new AnimatorSet();
        set.play(scaleX).with(scaleY);
        set.setDuration(2000);
        set.start();
    }

    public void alpha2(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "alpha", 1.0f, 0f, 0.9f);
        animator.setDuration(2000);
        animator.start();
    }

    public void zong2(View view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 2f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 1f, 2f, 1f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(imageView, "rotation", 0, 360f, 0, -360f);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(imageView, "translationX", 0f, 200f, 0f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0f, 1f);
        AnimatorSet set = new AnimatorSet();
        set.play(scaleX).with(scaleY).with(alpha).with(rotate).with(translationX);
        set.setDuration(3000);
        set.start();
    }
}
