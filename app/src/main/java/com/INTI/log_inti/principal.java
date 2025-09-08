
package com.INTI.log_inti;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class principal extends AppCompatActivity {

    private static final String TAG = "PrincipalActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        // Configurar botones con manejo de errores
        try {
            setupButtonWebPage(R.id.SOFWARE, "https://sites.google.com/inti.edu.sv/nuevoingreso/OfertaAcademica/desarrollo_de_software?authuser=0");
            setupButtonWebPage(R.id.TECNIA, "https://sites.google.com/inti.edu.sv/nuevoingreso/OfertaAcademica/sistemas_electricos?authuser=0");
            setupButtonWebPage(R.id.AUTO, "https://sites.google.com/inti.edu.sv/nuevoingreso/OfertaAcademica/mantenimiento_automotriz?authuser=0");
            setupButtonWebPage(R.id.ELECTRO, "https://sites.google.com/inti.edu.sv/nuevoingreso/OfertaAcademica/electronica?authuser=0");
            setupButtonWebPage(R.id.MECA, "https://sites.google.com/inti.edu.sv/nuevoingreso/OfertaAcademica/mecanica_industrial?authuser=0");
            setupButtonWebPage(R.id.ITSI, "https://sites.google.com/inti.edu.sv/nuevoingreso/OfertaAcademica/infraestructura_tecnologica_y_servicios_informaticos?authuser=0");
            setupButtonWebPage(R.id.FACE, "https://www.facebook.com/tecnicosinti");
            setupButtonWebPage(R.id.MATRI, "https://mn03sgo.github.io/inti2.github.io/ingre.html");

            Toast.makeText(this, "Botones configurados correctamente", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "Error al configurar botones: " + e.getMessage());
            Toast.makeText(this, "Error al configurar botones", Toast.LENGTH_LONG).show();
        }
    }

    private void setupButtonWebPage(int buttonId, final String url) {
        try {
            Button button = findViewById(buttonId);
            if (button != null) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openWebPage(url);
                    }
                });
                Log.d(TAG, "Botón " + buttonId + " configurado correctamente");
            } else {
                Log.e(TAG, "Botón con ID " + buttonId + " no encontrado en el layout");
                Toast.makeText(principal.this, "Error: Botón no encontrado", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error al configurar botón " + buttonId + ": " + e.getMessage());
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void openWebPage(String url) {
        try {
            Uri webpage = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);  // Mejora compatibilidad para forzar navegador

            // Verificar si es un enlace de Facebook
            if (url.contains("facebook.com")) {
                // Usa un chooser para forzar selección: navegador o app de FB
                Intent chooser = Intent.createChooser(intent, "Abrir en navegador o Facebook");
                if (chooser.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                } else {
                    Toast.makeText(this, "No se encontró una aplicación para abrir el enlace", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "No hay aplicación para manejar la intent: " + url);
                }
            } else {
                // Para otros enlaces, comportamiento normal
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "No se encontró una aplicación para abrir el enlace", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "No hay aplicación para manejar la intent: " + url);
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error al abrir el enlace", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Error al abrir webpage: " + url + " - " + e.getMessage());
        }
    }
}