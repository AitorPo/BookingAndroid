package com.androidavanzado.bookingaitor.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Ciudad {
    private static final String ID_CIUDAD = "idCiudad";
    private static final String NOMBRE = "nombre";
    private static final String IMAGEN = "imagen";

    private int idCiudad;
    private String nombre, imagen;

    public Ciudad(){}

    public int getIdCiudad() { return idCiudad; }

    public void setIdCiudad(int idCiudad) { this.idCiudad = idCiudad; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getImagen() { return imagen; }

    public void setImagen(String imagen) { this.imagen = imagen; }

    @Override
    public String toString() {
        return "Ciudad{" +
                "idCiudad=" + idCiudad +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    public static ArrayList<Ciudad> getArrayListFromJSON(JSONArray listCiudad){
        ArrayList<Ciudad> ciudadArrayList = null;
        Ciudad ciudad = null;
        try{
            //Comprobamos sobre el JSONArray que pasamos por parámetro
            if (listCiudad != null && listCiudad.length() > 0){
                ciudadArrayList = new ArrayList<Ciudad>();
            }
            //Recorremos el JSONArray que recibimos como parámetro
            for (int i = 0; i < listCiudad.length(); i ++){
                /*Creamos JSONObject para recoger los elementos
                 * del JSONArray que pasamos como parámetro*/
                JSONObject jsonObject = listCiudad.getJSONObject(i);
                /*Creamos objeto de la clase en cuestión para "rellenarlo"
                 * y añadirlo a la lista*/
                ciudad = new Ciudad();
                ciudad.setIdCiudad(jsonObject.getInt(ID_CIUDAD));
                ciudad.setNombre(jsonObject.getString(NOMBRE));
                ciudad.setImagen(jsonObject.getString(IMAGEN));

                //Añadimos a la lista los objetos "rellenados"
                ciudadArrayList.add(ciudad);
            }
        } catch (JSONException jsone) {
            jsone.printStackTrace();
        }
        //devolvemos el ArrayList con los datos
        return ciudadArrayList;
    }
}
