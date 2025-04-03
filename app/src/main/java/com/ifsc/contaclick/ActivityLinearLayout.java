package com.ifsc.contaclick;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityLinearLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_linear_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button enviar = findViewById(R.id.enviar);
        EditText nome = findViewById(R.id.nome);
        EditText email = findViewById(R.id.email);
        RadioGroup radioGroup = findViewById(R.id.grupoRadio);

        enviar.setOnClickListener(b->{
            Toast toast = Toast.makeText(this, nome.getText().toString()+" - "+email.getText().toString(), Toast.LENGTH_LONG);
            toast.show();
        });
    }
}