package com.example.trabajo.datos;

import com.example.trabajo.model.TipoSensor;
import com.example.trabajo.model.Ubicacion;
import com.example.trabajo.model.Sensor;

import java.util.ArrayList;
import java.util.List;

public class Repositorio {

    private static Repositorio instance = null;
    private List<TipoSensor> listaTipoSensor;
    private List<Ubicacion> listaUbicaciones;
    private List<Sensor> listaSensores; // Nueva lista para almacenar los sensores

    protected Repositorio() {
        listaTipoSensor = new ArrayList<>();
        listaUbicaciones = new ArrayList<>();
        listaSensores = new ArrayList<>(); // Inicialización de la lista de sensores

        // Agregar tipos de sensor
        listaTipoSensor.add(new TipoSensor("Temperatura"));
        listaTipoSensor.add(new TipoSensor("Humedad"));

        // Ubicaciones de muestra
        listaUbicaciones.add(new Ubicacion("Invernadero", "Invernadero aislado"));
        listaUbicaciones.add(new Ubicacion("Asque", "Mediano de 100 personas"));
        listaUbicaciones.add(new Ubicacion("Sur", "Grande para 200 personas"));
    }

    // Singleton
    public static synchronized Repositorio getInstance() {
        if (instance == null) {
            instance = new Repositorio();
        }
        return instance;
    }

    // Obtener tipos de sensor
    public List<String> obtenerTiposSensor() {
        List<String> tipos = new ArrayList<>();
        for (TipoSensor tipo : listaTipoSensor) {
            tipos.add(tipo.getNombre());
        }
        return tipos;
    }

    // Obtener ubicaciones para el spinner
    public List<String> obtenerUbicaciones() {
        List<String> ubicaciones = new ArrayList<>();
        for (Ubicacion ubicacion : listaUbicaciones) {
            ubicaciones.add(ubicacion.getNombre());
        }
        return ubicaciones;
    }

    // Agregar una ubicación
    public void agregarUbicacion(String nombre, String descripcion) {
        listaUbicaciones.add(new Ubicacion(nombre, descripcion));
    }

    // Agregar un sensor a la lista
    public void agregarSensor(Sensor sensor) {
        listaSensores.add(sensor);
    }

    // Obtener lista de sensores para el adaptador
    public List<Sensor> obtenerSensores() {
        return new ArrayList<>(listaSensores);
    }
}
