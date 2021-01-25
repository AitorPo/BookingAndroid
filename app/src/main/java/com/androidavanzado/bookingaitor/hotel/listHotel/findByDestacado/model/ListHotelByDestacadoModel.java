package com.androidavanzado.bookingaitor.hotel.listHotel.findByDestacado.model;

import android.os.AsyncTask;
import android.util.Log;

import com.androidavanzado.bookingaitor.beans.Hotel;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByDestacado.contract.ListHotelByDestacadoContract;
import com.androidavanzado.bookingaitor.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class ListHotelByDestacadoModel implements ListHotelByDestacadoContract.Model {
    private ArrayList<Hotel> hotelArrayList;
    OnListHotelByDestacadoListener onListHotelByDestacadoListener;

    @Override
    public void getDestacadosLH(final OnListHotelByDestacadoListener onListHotelByDestacadoListener) {
        this.onListHotelByDestacadoListener = onListHotelByDestacadoListener;
        HashMap<String, String> param = new HashMap<>();
        param.put("ACTION", "HOTEL");
        param.put("QUERY", "FIND_BY_DESTACADO");

        HotelDestacadoAsyncTask hotelDestacadoAsyncTask = new HotelDestacadoAsyncTask(param);
        hotelDestacadoAsyncTask.execute("http://192.168.1.68:8080/BookingAitor/Controller");
    }

    class HotelDestacadoAsyncTask extends AsyncTask<String, Integer, Boolean>{
        private HashMap<String, String> parametros = null;

        public HotelDestacadoAsyncTask(HashMap<String, String> parametros){
            super();
            this.parametros = parametros;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            String select = strings[0];

            try{
                Post post = new Post();
                JSONArray result = post.getServerDataPost(parametros, select);
                hotelArrayList = Hotel.getArrayListFromJSON(result);
            }catch (Exception e){
                Log.e("log_tag", "Error in http connection " + e.toString());
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean res) {
            if(res){
                onListHotelByDestacadoListener.onResolve(hotelArrayList);
            } else{
                onListHotelByDestacadoListener.onReject("Error al traer los datos desde el servidor");
            }
        }
    }
}
