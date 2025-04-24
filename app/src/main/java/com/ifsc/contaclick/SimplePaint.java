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
import java.util.Stack;

public class SimplePaint extends View {
    private float x0, y0;
    private Stack<Path> paths = new Stack<Path>();
    private Stack<Paint> paints = new Stack<Paint>();
    private Path currentPath;
    private Paint currentPaint;
    private Boolean lastDrawIsCurrent;
    private int forma;

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
        atualizaCurrent();
        currentPaint.setColor(Color.BLACK);
        currentPaint.setStrokeWidth(10);
        currentPaint.setStyle(Paint.Style.STROKE);
        forma=1;
    }

    public void atualizaCurrent(){
        currentPaint = new Paint();
        currentPath = new Path();
        if (!paints.isEmpty()){
            Paint ultimo = paints.lastElement();
            currentPaint.setColor(ultimo.getColor());
            currentPaint.setStyle(Paint.Style.STROKE);
            currentPaint.setStrokeWidth(ultimo.getStrokeWidth());
        }
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0;i<paths.size();i++) {
            canvas.drawPath(paths.get(i), paints.get(i));
        }
        canvas.drawPath(currentPath, currentPaint);
        lastDrawIsCurrent = true;
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

                if (forma == 1) {
                    currentPath.lineTo(event.getX(), event.getY());
                } else if (forma == 2 || forma == 3){
                    currentPath.reset();
                    currentPath.moveTo(x0, y0);
                    currentPath.lineTo(x0, event.getY());
                    currentPath.lineTo(event.getX(), event.getY());
                    currentPath.lineTo(event.getX(), y0);
                    currentPath.lineTo(x0, y0);

                    if (forma == 3){
                        currentPaint.setStyle(Paint.Style.FILL);
                    }
                } else if (forma == 4 || forma == 5){
                    currentPath.reset();
                    double x = Math.abs(x0 - event.getX());
                    double y = Math.abs(y0 - event.getY());
                    double raio = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
                    currentPath.addCircle(x0, y0, (float) raio, Path.Direction.CW);

                    if (forma == 5){
                        currentPaint.setStyle(Paint.Style.FILL);
                    }
                }

                this.invalidate();

                return true;
            }

            case MotionEvent.ACTION_UP:{
                int color = currentPaint.getColor();
                novaCamada();
                currentPaint.setColor(color);

                return true;
            }
        }

        return true;
    }

    public void limpa(){
        currentPath.reset();
        paths.clear();
        paints.clear();

        invalidate();
    }

    public void mudaCor(int color){
        novaCamada();
        currentPaint.setColor(color);
    }

    public void novaCamada(){
        paths.push(currentPath);
        paints.push(currentPaint);
        atualizaCurrent();
    }

    public void desfazer(){
        if (!paths.isEmpty()) {
            paths.pop();
            paints.pop();
        }

        invalidate();
    }

    public String trocaFormato(){
        String nome = null;

        if (forma == 5){
            forma = 1;
        }else{
            forma++;
        }

        switch (forma){
            case (1):
                nome = "Livre";
                break;
            case (2):
                nome = "Quadrado";
            break;
            case (3):
                nome = "Quadrado Cheio";
            break;
            case (4):
                nome = "Circulo";
            break;
            case (5):
                nome = "Circulo Cheio";
            break;
        }

        return nome;
    }
}