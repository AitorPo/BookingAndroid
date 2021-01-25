package com.androidavanzado.bookingaitor.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Hotel {
    private static final String ID_HOTEL ="idHotel";
    private static final String NOMBRE ="nombre";
    private static final String FOTO ="foto";
    private static final String DIRECCION ="direccion";
    private static final String CIUDAD ="ciudad";
    private static final String PUNTUACION ="puntuacion";
    private static final String NUM_HABITACIONES ="numHabitaciones";
    private static final String DESCRIPCION ="descripcion";
    private static final String NUM_RESERVAS ="numReservas";
    private static final String DESTACADO = "destacado";

    private int idHotel, puntuacion, numHabitaciones, numReservas, ciudad;
    private String nombre, foto, direccion, descripcion;
    private boolean destacado;

    public Hotel(String nombre){
        this.nombre = nombre;
    }

    public Hotel(){}

    @Override
    public String toString() {
        return "Hotel{" +
                "idHotel=" + idHotel +
                ", puntuacion=" + puntuacion +
                ", numHabitaciones=" + numHabitaciones +
                ", numReservas=" + numReservas +
                ", nombre='" + nombre + '\'' +
                ", foto='" + foto + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", destacado=" + destacado +
                '}';
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCiudad() {
        return ciudad;
    }

    public void setCiudad(int ciudad) {
        this.ciudad = ciudad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getNumHabitaciones() { return numHabitaciones; }

    public void setNumHabitaciones(int numHabitaciones) { this.numHabitaciones = numHabitaciones; }

    public int getNumReservas() { return numReservas; }

    public void setNumReservas(int numReservas) { this.numReservas = numReservas; }

    public boolean getDestacado() { return destacado; }

    public void setDestacado(boolean destacado) { this.destacado = destacado; }

    public static ArrayList<Hotel> getArrayListFromJSON(JSONArray listHotel){
        ArrayList<Hotel> arrayListHotel = null;
        Hotel hotel = null;
        try{
        //Comprobamos sobre el JSONArray que pasamos por parámetro
        if (listHotel != null && listHotel.length() > 0){
            arrayListHotel = new ArrayList<Hotel>();
        }
        
        //Recorremos el JSONArray que recibimos como parámetro

            for (int i = 0; i < listHotel.length(); i ++){
                /*Creamos JSONObject para recoger los elementos
                 * del JSONArray que pasamos como parámetro*/
                JSONObject jsonObject = listHotel.getJSONObject(i);
                /*Creamos objeto de la clase en cuestión para "rellenarlo"
                 * y añadirlo a la lista*/
                hotel = new Hotel();
                hotel.setIdHotel(jsonObject.getInt(ID_HOTEL));
                hotel.setNombre(jsonObject.getString(NOMBRE));
                hotel.setFoto(jsonObject.getString(FOTO));
                hotel.setDireccion(jsonObject.getString(DIRECCION));
                hotel.setCiudad(jsonObject.getInt(CIUDAD));
                hotel.setPuntuacion(jsonObject.getInt(PUNTUACION));
                hotel.setNumHabitaciones(jsonObject.getInt(NUM_HABITACIONES));
                hotel.setDescripcion(jsonObject.getString(DESCRIPCION));
                hotel.setNumReservas(jsonObject.getInt((NUM_RESERVAS)));
                hotel.setDestacado(jsonObject.getBoolean(DESTACADO));
                //Añadimos a la lista los objetos "rellenados"
                arrayListHotel.add(hotel);
            }
        } catch (JSONException jsone) {
            jsone.printStackTrace();
        }
        //devolvemos el ArrayList con los datos
        return arrayListHotel;
    }
}
