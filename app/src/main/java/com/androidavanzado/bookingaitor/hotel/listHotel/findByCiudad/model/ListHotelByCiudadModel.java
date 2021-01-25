package com.androidavanzado.bookingaitor.hotel.listHotel.findByCiudad.model;

import android.os.AsyncTask;
import android.util.Log;

import com.androidavanzado.bookingaitor.beans.Hotel;
import com.androidavanzado.bookingaitor.ciudad.listCiudad.findAll.view.ListCiudadActivity;
import com.androidavanzado.bookingaitor.hotel.detailsHotel.model.DetailsHotelModel;
import com.androidavanzado.bookingaitor.hotel.listHotel.findAll.view.ListHotelActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByCiudad.contract.ListHotelByCiudadContract;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByCiudad.view.ListHotelByCiudadActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByDestacado.view.ListHotelByDestacadoActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByPuntuacion.view.ListHotelByPuntuacionActivity;
import com.androidavanzado.bookingaitor.utils.Post;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ListHotelByCiudadModel implements ListHotelByCiudadContract.Model {
    //int idCiudad =
    //String idCiudadString = String.valueOf(idCiudad);
    ArrayList<Hotel> hotelArrayList;
    OnListHotelByCiudadListener onListHotelByCiudadListener;


    @Override
    public void listHotelByCiudadLH(OnListHotelByCiudadListener onListHotelByCiudadListener) {
        this.onListHotelByCiudadListener = onListHotelByCiudadListener;

        ListHotelByCiudadAsyncTask listHotelByCiudadAsyncTask = new ListHotelByCiudadAsyncTask();
        listHotelByCiudadAsyncTask.execute();
        //reseteo de los id para poder seguir viendo los detalles de los hoteles


    }

    class ListHotelByCiudadAsyncTask extends AsyncTask<String, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(String... strings) {

            String url_select = "http://192.168.1.68:8080/BookingAitor/Controller?ACTION=HOTEL&QUERY=FIND_BY_CIUDAD&ID=" + ListCiudadActivity.idCiudad;;
            try{
                Post post = new Post();
                JSONArray result = post.getServerDataGet(url_select);
                hotelArrayList = Hotel.getArrayListFromJSON(result);
                ListCiudadActivity.idCiudad = 0;
            } catch (Exception e){
                Log.e("log_tag", "Error in http connection " + e.toString());
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean res) {
            if(res){
                onListHotelByCiudadListener.onResolve(hotelArrayList);
            } else {
                onListHotelByCiudadListener.onReject("Error al traer los datos del servidor");
            }
        }
    }
}
