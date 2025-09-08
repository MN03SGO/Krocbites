package com.INTI.log_inti;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar el botón para ir a la actividad principal
        Button botonIngre = findViewById(R.id.boton_INGRE);
        botonIngre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la actividad principal
                Intent intent = new Intent(MainActivity.this, principal.class);
                startActivity(intent);

                // Opcional: cerrar esta actividad para que no se pueda volver atrás
                // finish();
            }
        });
    }
}