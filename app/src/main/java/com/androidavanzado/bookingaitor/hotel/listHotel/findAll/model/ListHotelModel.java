package com.androidavanzado.bookingaitor.hotel.listHotel.findAll.model;


import android.os.AsyncTask;
import android.util.Log;

import com.androidavanzado.bookingaitor.beans.Hotel;
import com.androidavanzado.bookingaitor.hotel.listHotel.findAll.contract.ListHotelContract;
import com.androidavanzado.bookingaitor.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class ListHotelModel implements ListHotelContract.Model {
    //private static final String URL = "http://10.0.2.2 :8080/BookingAitor/Controller?ACTION=HOTEL&QUERY=FIND_ALL";
    //TODO añadir ruta a la URL si no consigo hacer funcionar HasMap
    //?ACTION=HOTEL&QUERY=FIND_ALL
    ArrayList<Hotel> hotelArrayList;
    OnListHotelListener onListHotelListener;

    //getHotelesLocalHost
    @Override
    public void getHotelesLH(final OnListHotelListener onListHotelListener) {
        this.onListHotelListener = onListHotelListener;
        HashMap<String, String> param = new HashMap<>();

        param.put("ACTION", "HOTEL");
        param.put("QUERY", "FIND_ALL");
        HotelAsyncTask hotelAsyncTask = new HotelAsyncTask(param);
        hotelAsyncTask.execute("http://192.168.1.68:8080/BookingAitor/Controller");

    }



    class HotelAsyncTask extends AsyncTask<String, Integer, Boolean> {

        private HashMap<String, String> parametros = null;
        public HotelAsyncTask(HashMap<String, String> parametros){
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

                    onListHotelListener.onResolve(hotelArrayList);

            } else{
                onListHotelListener.onReject("Error");
            }

        }
    }
}


