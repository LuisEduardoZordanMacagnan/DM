package com.ifsc.contaclick;

import static android.view.Gravity.CENTER;
import static android.widget.LinearLayout.VERTICAL;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityTelaProgramatica extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        LinearLayout linear = new LinearLayout(this);
        linear.setOrientation(VERTICAL);
        linear.setGravity(CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        linear.setLayoutParams(params);

        TextView textView = new TextView(this);
        textView.setText("Olá Mundo");
        linear.addView(textView);

        Button button = new Button(this);
        button.setText("Clique Aqui");
        linear.addView(button);

        setContentView(linear);
    }
}