package com.androidavanzado.bookingaitor.ciudad.listCiudad.findAll.model;

import android.os.AsyncTask;
import android.util.Log;

import com.androidavanzado.bookingaitor.beans.Ciudad;
import com.androidavanzado.bookingaitor.ciudad.listCiudad.findAll.contract.ListCiudadContract;
import com.androidavanzado.bookingaitor.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class ListCiudadModel implements ListCiudadContract.Model {
    private ArrayList<Ciudad> ciudades;
    private OnListCiudadModelListener onListCiudadModelListener;

    @Override
    public void getListCiudadLH(final OnListCiudadModelListener onListCiudadModelListener) {
        this.onListCiudadModelListener = onListCiudadModelListener;
        HashMap<String, String> param = new HashMap<>();
        param.put("ACTION", "CIUDAD");
        param.put("QUERY", "FIND_ALL");

        ListCiudadAsyncTask listCiudadAsyncTask = new ListCiudadAsyncTask(param);
        listCiudadAsyncTask.execute("http://192.168.1.68:8080/BookingAitor/Controller");
    }

    class ListCiudadAsyncTask extends AsyncTask<String, Integer, Boolean>{
        private HashMap<String, String> parametros = null;
        public ListCiudadAsyncTask(HashMap<String, String> parametros){
            super();
            this.parametros = parametros;
        }
        @Override
        protected Boolean doInBackground(String... strings) {
            String select = strings[0];
            try{
                Post post = new Post();
                JSONArray result = post.getServerDataPost(parametros, select);
                ciudades = Ciudad.getArrayListFromJSON(result);
            } catch (Exception e){
                Log.e("log_tag", "Error in http connection " + e.toString());
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean res) {
            if(res){
                onListCiudadModelListener.onResolve(ciudades);
            } else {
                onListCiudadModelListener.onReject("Error al traer los datos desde el servidor");
            }
        }
    }
}
