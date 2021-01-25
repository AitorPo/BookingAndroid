package com.androidavanzado.bookingaitor.habitacion.findByPrecioDesc.model;

import android.os.AsyncTask;
import android.util.Log;

import com.androidavanzado.bookingaitor.beans.Habitacion;
import com.androidavanzado.bookingaitor.habitacion.findByPrecioDesc.contract.ListHabitacionByPrecioDescContract;
import com.androidavanzado.bookingaitor.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class ListHabitacionByPrecioDescModel implements ListHabitacionByPrecioDescContract.Model {
    private ArrayList<Habitacion> habitaciones;
    private OnListPrecioDescListener onListPrecioDescListener;


    @Override
    public void listHabitacionPrecioDesc(OnListPrecioDescListener onListPrecioDescListener) {
        this.onListPrecioDescListener = onListPrecioDescListener;
        HashMap<String, String> param = new HashMap<>();
        param.put("ACTION", "HABITACION");
        param.put("QUERY", "FIND_BY_PRECIO_DESC");
        PrecioAscAsyncTask precioAscAsyncTask = new PrecioAscAsyncTask(param);
        precioAscAsyncTask.execute("http://192.168.1.68:8080/BookingAitor/Controller");
    }

    class PrecioAscAsyncTask extends AsyncTask<String, Integer, Boolean>{
        private HashMap<String, String> parametros = null;
        public PrecioAscAsyncTask(HashMap<String, String> parametros){
            super();
            this.parametros = parametros;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            String select = strings[0];
            try{
                Post post = new Post();
                JSONArray result = post.getServerDataPost(parametros, select);
                habitaciones = Habitacion.getArrayListFromJSON(result);
            } catch (Exception e){
                Log.e("log_tag", "Error in http connection " + e.toString());
            }

        return true;
        }

        @Override
        protected void onPostExecute(Boolean res) {
            if(res){
                onListPrecioDescListener.onResolve(habitaciones);
            } else{
                onListPrecioDescListener.onReject("Error al traer los datos del servidor");
            }
        }
    }
}
