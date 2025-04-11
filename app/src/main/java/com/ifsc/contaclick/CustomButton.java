package com.ifsc.contaclick;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomButton extends androidx.appcompat.widget.AppCompatButton {
    float x0, y0, xinicial, anguloinicial;

    public CustomButton(@NonNull Context context) {
        super(context);
    }

    public CustomButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("Coordenadas", Float.toString(event.getRawX())+" - "+Float.toString(event.getRawY()));
        float x, y;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN: {
                x0 = event.getRawX();
                y0 = event.getRawY();
                xinicial = this.getX();
                anguloinicial = this.getRotation();
                return true;
            }

            case MotionEvent.ACTION_MOVE:{
                float delta = event.getRawX()-x0;

                this.setX(delta+xinicial);

                float normalizacao = Math.max(-1f, Math.min(1f, delta / getWidth()));

                int red = (int) (255 * (1- normalizacao) /2);
                int green = (int) (255 * (1+ normalizacao) /2);

                this.setBackgroundColor(Color.rgb(red, green, 0));

                if (x0<=event.getRawX()){
                    this.setRotation(10 * (1+normalizacao));
                }else{
                    this.setRotation(-10 * (1-normalizacao));
                }

                return true;
            }

            case MotionEvent.ACTION_UP:{
                this.setBackgroundColor(Color.GRAY);
                this.setRotation(anguloinicial);

                ObjectAnimator animator = ObjectAnimator.ofFloat(this, "x", this.getX(), xinicial);
                animator.setDuration(500);
                animator.start();

                return true;
            }
        }

        return true;
    }
}
