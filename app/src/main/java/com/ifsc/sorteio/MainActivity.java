package com.ifsc.sorteio;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button botao;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        botao = findViewById(R.id.button);
        editText = findViewById(R.id.editTextText);

        botao.setOnClickListener(b->{
            Intent i = new Intent(getApplicationContext(), MsgActivity.class);
            String msg = editText.getText().toString();
            i.putExtra("msg", msg);
            startActivity(i);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Log.d("ciclodevida", "onCreate");
        Toast.makeText(this, "Sussus Amogus", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ciclodevida", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ciclodevida", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ciclodevida", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("ciclodevida", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("ciclodevida", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ciclodevida", "onDestroy");
    }
}