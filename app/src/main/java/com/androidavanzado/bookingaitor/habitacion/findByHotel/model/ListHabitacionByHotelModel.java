package com.androidavanzado.bookingaitor.habitacion.findByHotel.model;

import android.os.AsyncTask;
import android.util.Log;

import com.androidavanzado.bookingaitor.beans.Habitacion;
import com.androidavanzado.bookingaitor.habitacion.findByHotel.contract.ListHabitacionByHotelContract;
import com.androidavanzado.bookingaitor.habitacion.findByHotel.view.ListHabitacionByHotelActivity;
import com.androidavanzado.bookingaitor.hotel.detailsHotel.model.DetailsHotelModel;
import com.androidavanzado.bookingaitor.hotel.detailsHotel.view.DetailsHotelActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByCiudad.view.ListHotelByCiudadActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByDestacado.view.ListHotelByDestacadoActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByPuntuacion.view.ListHotelByPuntuacionActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByReservas.view.ListHotelByReservasActivity;
import com.androidavanzado.bookingaitor.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class ListHabitacionByHotelModel implements ListHabitacionByHotelContract.Model {
    private ArrayList<Habitacion> habitaciones;
    private OnListHabitacionByHotelListener onListHabitacionByHotelListener;
    public static int idHotel;

    @Override
    public void getHabitacionListLH(OnListHabitacionByHotelListener onListHabitacionByHotelListener) {
        this.onListHabitacionByHotelListener = onListHabitacionByHotelListener;

        ListHabitacionByHotelAsyncTask listHabitacionByHotelAsyncTask = new ListHabitacionByHotelAsyncTask();
        if (DetailsHotelActivity.idHotel != 0){
            this.idHotel = DetailsHotelActivity.idHotel;
        } else if (ListHabitacionByHotelActivity.idHotel != 0){
            this.idHotel = ListHabitacionByHotelActivity.idHotel;
        } else if (ListHotelByCiudadActivity.idHotel != 0){
            this.idHotel = ListHotelByCiudadActivity.idHotel;
        } else if (ListHotelByDestacadoActivity.idHotel != 0){
            this.idHotel = ListHotelByDestacadoActivity.idHotel;
        } else if (ListHotelByPuntuacionActivity.idHotel != 0){
            this.idHotel = ListHotelByPuntuacionActivity.idHotel;
        } else if (ListHotelByReservasActivity.idHotel != 0){
            this.idHotel = ListHotelByReservasActivity.idHotel;
        }

        listHabitacionByHotelAsyncTask.execute("http://192.168.1.68:8080/BookingAitor/Controller?ACTION=HABITACION&QUERY=FIND_ALL_BY_HOTEL&ID_HOTEL=" + idHotel);

        DetailsHotelActivity.idHotel = 0;
        ListHabitacionByHotelActivity.idHotel = 0;
        ListHotelByCiudadActivity.idHotel = 0;
        ListHotelByDestacadoActivity.idHotel = 0;
        ListHotelByPuntuacionActivity.idHotel = 0;
        ListHotelByReservasActivity.idHotel = 0;
    }

    class ListHabitacionByHotelAsyncTask extends AsyncTask<String, Integer, Boolean>{
        @Override
        protected Boolean doInBackground(String... strings) {
            String URL = strings[0];
            try{
                Post post = new Post();
                JSONArray result = post.getServerDataGet(URL);
                habitaciones = Habitacion.getArrayListFromJSON(result);
            } catch (Exception e){
                Log.e("log_tag", "Error in http connection " + e.toString());
            }

            return true;

        }

        @Override
        protected void onPostExecute(Boolean res) {
            if(res){
                onListHabitacionByHotelListener.onResolve(habitaciones);
            } else {
                onListHabitacionByHotelListener.onReject("Error al traer los datos del servidor");
            }
        }
    }
}
