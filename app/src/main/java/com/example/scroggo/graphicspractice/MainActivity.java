package com.example.scroggo.graphicspractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.text_view);
        textView.setBackgroundResource(R.drawable.unc_android);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.rotate);
        textView.startAnimation(anim);

        ObjectAnimator anim2 =
                ObjectAnimator.ofFloat(textView, "textSize", 10f, 40f);
        anim2.setDuration(5000);
        anim2.start();

        final ImageView imageView = findViewById(R.id.imageView);
        AnimationDrawable drawable = (AnimationDrawable) imageView.getDrawable();
        drawable.start();
        final ConstraintLayout.LayoutParams birdParams
                = (ConstraintLayout.LayoutParams) imageView.getLayoutParams();


        ValueAnimator flyAnimation = ValueAnimator.ofFloat(0f, 500f);
        flyAnimation.setDuration(1000);
        flyAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                birdParams.rightMargin = ((Float) valueAnimator.getAnimatedValue()).intValue();
                // Online documentation suggested that the following line is necessary, but
                // it caused problems for me.
                //imageView.setLayoutParams(birdParams);
            }
        });
        flyAnimation.start();

        ImageView transition = findViewById(R.id.imageView2);
        TransitionDrawable transitionDrawable =
                (TransitionDrawable) getResources().getDrawable(R.drawable.transition, null);
        transitionDrawable.setCrossFadeEnabled(true);
        transition.setImageDrawable(transitionDrawable);
        transitionDrawable.startTransition(1000);
    }
}
