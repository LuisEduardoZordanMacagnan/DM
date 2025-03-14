package com.ifsc.sorteio;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button botao = findViewById(R.id.button);
        TextView saida = findViewById(R.id.texto);
        TextView t1 = findViewById(R.id.n1);
        TextView t2 = findViewById(R.id.n2);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!t1.getText().toString().isEmpty() || !t2.getText().toString().isEmpty()){
                    Integer min = Integer.parseInt(t1.getText().toString()), max = Integer.parseInt(t2.getText().toString());
                    if (min>max){
                        min = max;
                        max = Integer.parseInt(t1.getText().toString());
                    }

                    Integer resul = Math.toIntExact(Math.round((Math.random()*(max-min))+min));

                    saida.setText(resul.toString());
                }
            }
        });


    }
}