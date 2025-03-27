package com.ifsc.sorteio;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT;

import android.content.Intent;
import android.health.connect.datatypes.units.Length;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    EditText alturaText;
    EditText pesoText;
    Button botao;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        i = new Intent(getApplicationContext(), MsgActivity.class);
        botao = findViewById(R.id.button);
        pesoText = findViewById(R.id.peso);
        alturaText = findViewById(R.id.altura);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        botao.setOnClickListener(b->{
            try {
                Float altura = Float.parseFloat(alturaText.getText().toString());
                Float peso = Float.parseFloat(pesoText.getText().toString());
                Float imc = peso/(altura*altura);
                i.putExtra("imc", imc.toString());
                startActivity(i);
            }catch (NumberFormatException e){
                Snackbar mySnackbar = Snackbar.make(findViewById(R.id.main), "Erro: "+e.getMessage(), LENGTH_SHORT);
                mySnackbar.show();
            }
        });
    }
}