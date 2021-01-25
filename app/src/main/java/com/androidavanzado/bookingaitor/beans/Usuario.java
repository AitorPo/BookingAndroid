package com.androidavanzado.bookingaitor.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Usuario {
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String ID = "ID";

    private String email, password;
    private int id;

    public Usuario(){}

    @Override
    public String toString() {
        return "Usuario{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static ArrayList<Usuario> getArrayListFromJSON(JSONArray listUsuario){
        ArrayList<Usuario> usuarioArrayList = null;
        Usuario usuario = null;
        try{
            //Comprobamos sobre el JSONArray que pasamos por parámetro
            if (listUsuario != null && listUsuario.length() > 0){
                usuarioArrayList = new ArrayList<Usuario>();
            }
            //Recorremos el JSONArray que recibimos como parámetro
            for (int i = 0; i < listUsuario.length(); i ++){
                /*Creamos JSONObject para recoger los elementos
                 * del JSONArray que pasamos como parámetro*/
                JSONObject jsonObject = listUsuario.getJSONObject(i);
                /*Creamos objeto de la clase en cuestión para "rellenarlo"
                 * y añadirlo a la lista*/
                usuario = new Usuario();
                usuario.setEmail(jsonObject.getString(EMAIL));
                usuario.setPassword(jsonObject.getString(PASSWORD));
                //Añadimos a la lista los objetos "rellenados"
                usuarioArrayList.add(usuario);
            }
        } catch (JSONException jsone) {
            jsone.printStackTrace();
        }
        //devolvemos el ArrayList con los datos
        return usuarioArrayList;
    }
}
