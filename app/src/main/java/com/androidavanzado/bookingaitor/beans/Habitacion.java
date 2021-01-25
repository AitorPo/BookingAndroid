package com.androidavanzado.bookingaitor.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Habitacion {
    public static final String ID_HABITACION = "idHabitacion";
    public static final String ID_HOTEL = "idHotel";
    public static final String NUM_HABITACION = "numHabitacion";
    public static final String CAMAS = "camas";
    public static final String OCUPADA = "ocupada";
    public static final String PRECIO = "precio";
    public static final String DESCRIPCION = "descripcion";
    public static final String FOTO = "foto";


    private int idHabitacion, idHotel, camas, numHabitacion;
    private double precio;
    private String descripcion, foto;
    private boolean ocupada;

    public Habitacion(){}

    @Override
    public String toString() {
        return "Habitacion{" +
                "idHabitacion=" + idHabitacion +
                ", idHotel=" + idHotel +
                ", camas=" + camas +
                ", numHabitacion=" + numHabitacion +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", foto='" + foto + '\'' +
                ", ocupada=" + ocupada +
                '}';
    }

    public String getFoto() { return foto; }

    public void setFoto(String foto) { this.foto = foto; }

    public int getIdHabitacion() { return idHabitacion; }

    public void setIdHabitacion(int idHabitacion) { this.idHabitacion = idHabitacion; }

    public int getIdHotel() { return idHotel; }

    public void setIdHotel(int idHotel) { this.idHotel = idHotel; }

    public int getCamas() { return camas; }

    public void setCamas(int camas) { this.camas = camas; }

    public int getNumHabitacion() { return numHabitacion; }

    public void setNumHabitacion(int numHabitacion) { this.numHabitacion = numHabitacion; }

    public double getPrecio() { return precio; }

    public void setPrecio(double precio) { this.precio = precio; }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public boolean isOcupada() { return ocupada; }

    public void setOcupada(boolean ocupada) { this.ocupada = ocupada; }



    public static ArrayList<Habitacion> getArrayListFromJSON(JSONArray listHabitacion){
        ArrayList<Habitacion> habitacionArrayList = null;
        Habitacion habitacion = null;
        try{
            //Comprobamos sobre el JSONArray que pasamos por parámetro
            if (listHabitacion != null && listHabitacion.length() > 0){
                habitacionArrayList = new ArrayList<Habitacion>();
            }
            //Recorremos el JSONArray que recibimos como parámetro
            for (int i = 0; i < listHabitacion.length(); i ++){
                /*Creamos JSONObject para recoger los elementos
                 * del JSONArray que pasamos como parámetro*/
                JSONObject jsonObject = listHabitacion.getJSONObject(i);
                /*Creamos objeto de la clase en cuestión para "rellenarlo"
                 * y añadirlo a la lista*/
                habitacion = new Habitacion();
                habitacion.setIdHabitacion(jsonObject.getInt(ID_HABITACION));
                habitacion.setIdHotel(jsonObject.getInt(ID_HOTEL));
                habitacion.setNumHabitacion(jsonObject.getInt(NUM_HABITACION));
                habitacion.setCamas(jsonObject.getInt(CAMAS));
                habitacion.setOcupada(jsonObject.getBoolean(OCUPADA));
                habitacion.setPrecio(jsonObject.getDouble(PRECIO));
                habitacion.setDescripcion(jsonObject.getString(DESCRIPCION));
                habitacion.setFoto(jsonObject.getString(FOTO));


                //Añadimos a la lista los objetos "rellenados"
                habitacionArrayList.add(habitacion);
            }
        } catch (JSONException jsone) {
            jsone.printStackTrace();
        }
        //devolvemos el ArrayList con los datos
        return habitacionArrayList;
    }
}
