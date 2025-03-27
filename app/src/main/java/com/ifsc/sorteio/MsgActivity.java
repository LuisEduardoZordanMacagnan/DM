package com.ifsc.sorteio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MsgActivity extends AppCompatActivity {
    TextView textView;
    Button b;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_msg);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        b = findViewById(R.id.voltar);
        i = new Intent(getApplicationContext(), MainActivity.class);

        textView = findViewById(R.id.textView2);
        Bundle bundle = getIntent().getExtras();
        Float imc = Float.parseFloat(bundle.getString("imc"));

        String mensagem=imc.toString()+"\n";
        ImageView image = findViewById(R.id.image);
        if(imc>=40f){
            image.setImageResource(R.drawable.obesidade3);
            mensagem+="Obesidade 3";
        }else if(imc>=35f){
            image.setImageResource(R.drawable.obesidade2);
            mensagem+="Obesidade 2";
        }else if(imc>=30f){
            image.setImageResource(R.drawable.obesidade1);
            mensagem+="Obesidade 1";
        }else if(imc>=25f){
            image.setImageResource(R.drawable.sobrepeso);
            mensagem+="Sobrepeso";
        }else if(imc>=18.5){
            image.setImageResource(R.drawable.normal);
            mensagem+="Peso normal";
        }else{
            mensagem+="Baixo Peso";
        }

        textView.setText(mensagem);

        b.setOnClickListener(b->{
            startActivity(i);
        });
    }
}