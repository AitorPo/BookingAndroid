package com.androidavanzado.bookingaitor.hotel.listHotel.findByReservas.model;

import android.os.AsyncTask;
import android.util.Log;

import com.androidavanzado.bookingaitor.beans.Hotel;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByReservas.contract.ListHotelByReservasContract;
import com.androidavanzado.bookingaitor.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class ListHotelByReservasModel implements ListHotelByReservasContract.Model {
    private ArrayList<Hotel> hotelArrayList;
    OnListHotelByReservasListener onListHotelByReservasListener;

    @Override
    public void getHotelesReservasLH(OnListHotelByReservasListener onListHotelByReservasListener) {
        this.onListHotelByReservasListener = onListHotelByReservasListener;
        HashMap<String, String> param = new HashMap<>();
        param.put("ACTION", "HOTEL");
        param.put("QUERY", "RANKING_RESERVAS");

        HotelesByReservasAsyncTask hotelesByReservasAsyncTask = new HotelesByReservasAsyncTask(param);
        hotelesByReservasAsyncTask.execute("http://192.168.1.68:8080/BookingAitor/Controller");
    }

    class HotelesByReservasAsyncTask extends AsyncTask<String, Integer, Boolean>{
        private HashMap<String, String> parametros = null;
        public HotelesByReservasAsyncTask(HashMap<String, String> parametros){
            super();
            this.parametros = parametros;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            String select = strings[0];

            try {
                Post post = new Post();
                JSONArray result = post.getServerDataPost(parametros, select);
                hotelArrayList = Hotel.getArrayListFromJSON(result);
            } catch (Exception e){
                Log.e("log_tag", "Error in http connection " + e.toString());
            }
            return true;

        }

        @Override
        protected void onPostExecute(Boolean res) {
            if (res){
                onListHotelByReservasListener.onResolve(hotelArrayList);
            } else {
                onListHotelByReservasListener.onReject("Error al traer los datos del servidor");
            }
        }
    }
}
