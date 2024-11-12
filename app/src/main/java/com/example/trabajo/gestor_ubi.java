package com.example.trabajo;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.trabajo.datos.Repositorio;

public class gestor_ubi extends AppCompatActivity {

    private EditText ubiSensorEditText;
    private EditText descripcionEditText;
    private Button ingresarUbiButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gestor_ubi);

        // Inicio vistas
        ubiSensorEditText = findViewById(R.id.UbiSensor2);
        descripcionEditText = findViewById(R.id.descripc);
        ingresarUbiButton = findViewById(R.id.ingresarUbiButton);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Agregar listener
        ingresarUbiButton.setOnClickListener(v -> validarDatos());
    }

    // En gestor_ubi
    private void validarDatos() {
        String nombre = ubiSensorEditText.getText().toString().trim();
        String descripcion = descripcionEditText.getText().toString().trim();

        if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(this, "¡El nombre es OBLIGATORIO!", Toast.LENGTH_SHORT).show();
            return;
        } else if (nombre.length() < 5 || nombre.length() > 15) {
            Toast.makeText(this, "El nombre debe tener entre 5 y 15 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!TextUtils.isEmpty(descripcion) && descripcion.length() > 30) {
            Toast.makeText(this, "La descripción no debe ser tan larga (max 30 caractéres)", Toast.LENGTH_SHORT).show();
            return;
        }

        // Guardar ubicación en el Repositorio
        Repositorio.getInstance().agregarUbicacion(nombre, descripcion);

        Toast.makeText(this, "Ubicación agregada correctamente", Toast.LENGTH_SHORT).show();
        finish(); // Cierra la actividad después de guardar la ubicación
    }


}