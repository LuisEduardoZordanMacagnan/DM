package com.ifsc.contaclick;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SimplePaint extends View {
    float x0, y0;
    ArrayList<Path> paths = new ArrayList<Path>();
    ArrayList<Paint> paints = new ArrayList<Paint>();
    Path currentPath;
    Paint currentPaint;

    public SimplePaint(Context context) {
        super(context);
        init();
    }

    public SimplePaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimplePaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SimplePaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init(){
        currentPaint = new Paint();
        currentPath = new Path();
        currentPaint.setColor(Color.BLACK);
        currentPaint.setStrokeWidth(10);
        currentPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        Path path;
        Paint paint;
        for(int i=0;i<paints.size();i++){
            path = paths.get(i);
            paint = paints.get(i);
            canvas.drawPath(currentPath, currentPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                x0=event.getX();
                y0=event.getY();
                currentPath.moveTo(x0, y0);

                return true;
            }

            case MotionEvent.ACTION_MOVE:{
                currentPath.lineTo(event.getX(), event.getY());
                this.invalidate();

                return true;
            }

            case MotionEvent.ACTION_UP:{


                return true;
            }
        }

        return true;
    }

    public void limpa(){
        currentPath.reset();
        invalidate();
    }

    public void mudaCor(int color){
        novaCamada();
        currentPaint.setColor(color);
    }

    public void novaCamada(){
        paths.add(currentPath);
        paints.add(currentPaint);
        init();
    }
}