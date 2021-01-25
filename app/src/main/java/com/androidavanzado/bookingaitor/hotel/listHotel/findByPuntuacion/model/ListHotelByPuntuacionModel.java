package com.androidavanzado.bookingaitor.hotel.listHotel.findByPuntuacion.model;

import android.os.AsyncTask;
import android.util.Log;

import com.androidavanzado.bookingaitor.beans.Hotel;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByPuntuacion.contract.ListHotelByPuntuacionContract;
import com.androidavanzado.bookingaitor.utils.Post;
import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class ListHotelByPuntuacionModel implements ListHotelByPuntuacionContract.Model {
    private ArrayList<Hotel> hotelArrayList;
    private OnListHotelByPuntuacionListener onListHotelByPuntuacionListener;

    @Override
    public void getListByPuntuacionLH(OnListHotelByPuntuacionListener onListHotelByPuntuacionListener) {
        this.onListHotelByPuntuacionListener = onListHotelByPuntuacionListener;
        HashMap<String, String> param = new HashMap<>();
        param.put("ACTION", "HOTEL");
        param.put("QUERY", "FIND_BY_PUNTUACION");

        HotelByPuntuacionAT hotelByPuntuacionAT = new HotelByPuntuacionAT(param);
        hotelByPuntuacionAT.execute("http://192.168.1.68:8080/BookingAitor/Controller");
    }

    class HotelByPuntuacionAT extends AsyncTask<String, Integer, Boolean>{
        private HashMap<String, String> parametros = null;

        public HotelByPuntuacionAT(HashMap<String, String> parametros){
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
                onListHotelByPuntuacionListener.onResolve(hotelArrayList);
            }else{
                onListHotelByPuntuacionListener.onReject("Error");
            }

        }
    }
}
