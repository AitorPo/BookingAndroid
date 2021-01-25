package com.androidavanzado.bookingaitor.hotel.detailsHotel.model;

import android.os.AsyncTask;
import android.util.Log;

import com.androidavanzado.bookingaitor.beans.Hotel;
import com.androidavanzado.bookingaitor.hotel.detailsHotel.contract.DetailsHotelContract;
import com.androidavanzado.bookingaitor.hotel.listHotel.findAll.view.ListHotelActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByCiudad.view.ListHotelByCiudadActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByDestacado.view.ListHotelByDestacadoActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByPuntuacion.view.ListHotelByPuntuacionActivity;
import com.androidavanzado.bookingaitor.utils.Post;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailsHotelModel implements DetailsHotelContract.Model {
    public static int idHotel;

    //String idHotelToString = String.valueOf(idHotel);
    private ArrayList<Hotel> detailsHotelArrayList;
    OnDetailsHotelListener onDetailsHotelListener;


    @Override
    public void getHotelLS(OnDetailsHotelListener onDetailsHotelListener) {
        this.onDetailsHotelListener = onDetailsHotelListener;

        DetailsHotelAsyncTask detailsHotelAsyncTask = new DetailsHotelAsyncTask();
        //Gestión de id para ver los detalles del hotel según el activity desde el que se clique la card
        if(ListHotelActivity.idHotel != 0){
            this.idHotel = ListHotelActivity.idHotel;
        } else if (ListHotelByPuntuacionActivity.idHotel != 0){
            this.idHotel = ListHotelByPuntuacionActivity.idHotel;
        } else if (ListHotelByDestacadoActivity.idHotel != 0){
            this.idHotel = ListHotelByDestacadoActivity.idHotel;
        } else if (ListHotelByCiudadActivity.idHotel != 0){
            this.idHotel = ListHotelByCiudadActivity.idHotel;
        }
        detailsHotelAsyncTask.execute("http://192.168.1.68:8080/BookingAitor/Controller?ACTION=HOTEL&QUERY=FIND_BY_ID&ID=" + this.idHotel);
        //reseteo de los id para poder seguir viendo los detalles de los hoteles
        ListHotelActivity.idHotel = 0;
        ListHotelByPuntuacionActivity.idHotel = 0;
        ListHotelByDestacadoActivity.idHotel = 0;
        ListHotelByCiudadActivity.idHotel = 0;
    }

    class DetailsHotelAsyncTask extends AsyncTask <String, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(String... strings) {
            String url_select = strings[0];
            try{
                Post post = new Post();
                JSONObject objectHotelDetails = post.getServerDataGetObject(url_select);
                JSONArray jsonDetailsList = new JSONArray();
                jsonDetailsList.put(objectHotelDetails);
                detailsHotelArrayList = Hotel.getArrayListFromJSON(jsonDetailsList);
            } catch (Exception e){
                Log.e("log_tag", "Error in http connection " + e.toString());
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean res) {
            if(res){
                onDetailsHotelListener.onResolve(detailsHotelArrayList);
            }else{
                onDetailsHotelListener.onReject("Error");
            }
        }
    }
}
