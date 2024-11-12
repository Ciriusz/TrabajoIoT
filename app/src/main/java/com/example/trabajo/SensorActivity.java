package com.example.trabajo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabajo.datos.Repositorio;
import com.example.trabajo.model.Sensor;

import java.util.List;

public class SensorActivity extends AppCompatActivity {

    private EditText nombreEditText;
    private EditText descripcionEditText;
    private EditText idealEditText;
    private Button ingresarSensorButton;
    private Button verSensoresButton;  // Botón para ver sensores agregados

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        // Inicializar campos de texto y botones
        nombreEditText = findViewById(R.id.sensores);
        descripcionEditText = findViewById(R.id.modeloSensorEditText);
        idealEditText = findViewById(R.id.idealEditText);
        ingresarSensorButton = findViewById(R.id.ingresarSensorButton);
        verSensoresButton = findViewById(R.id.SensoresHistorial);

        // Spinner de Tipo de Sensor y Ubicación
        Spinner spinnerTipoSensor = findViewById(R.id.tipoSensorSpinner);
        Spinner spinnerUbicacion = findViewById(R.id.ubicacionSpinner);

        // Obtener datos desde el Repositorio
        List<String> tiposSensor = Repositorio.getInstance().obtenerTiposSensor();
        List<String> ubicaciones = Repositorio.getInstance().obtenerUbicaciones();

        // Configurar adaptadores para los Spinners
        ArrayAdapter<String> tipoSensorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tiposSensor);
        tipoSensorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoSensor.setAdapter(tipoSensorAdapter);

        ArrayAdapter<String> ubicacionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ubicaciones);
        ubicacionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUbicacion.setAdapter(ubicacionAdapter);

        // Configurar botón para ingresar sensor
        ingresarSensorButton.setOnClickListener(v -> validarDatos());

        // Configurar botón para ver sensores agregados
        verSensoresButton.setOnClickListener(v -> {
            Intent intent = new Intent(SensorActivity.this, RecyclerVerSensoresActivity.class);
            startActivity(intent);
        });
    }

    private void validarDatos() {
        String nombre = nombreEditText.getText().toString().trim();
        String descripcion = descripcionEditText.getText().toString().trim();
        String idealStr = idealEditText.getText().toString().trim();

        if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(this, "El nombre es obligatorio", Toast.LENGTH_SHORT).show();
            return;
        } else if (nombre.length() < 5 || nombre.length() > 15) {
            Toast.makeText(this, "El nombre debe tener entre 5 y 15 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!TextUtils.isEmpty(descripcion) && descripcion.length() > 30) {
            Toast.makeText(this, "La descripción no debe exceder los 30 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(idealStr)) {
            Toast.makeText(this, "El valor ideal es obligatorio", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            float ideal = Float.parseFloat(idealStr);
            if (ideal <= 0) {
                Toast.makeText(this, "El valor ideal debe ser un número positivo", Toast.LENGTH_SHORT).show();
                return;
            }

            Sensor sensor = new Sensor(nombre, descripcion, ideal);
            Repositorio.getInstance().agregarSensor(sensor);
            Toast.makeText(this, "Sensor agregado correctamente", Toast.LENGTH_SHORT).show();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "El valor ideal debe ser un número válido", Toast.LENGTH_SHORT).show();
        }
    }
}

