package com.androidavanzado.bookingaitor.habitacion.detailsHabitacion.model;

import android.os.AsyncTask;
import android.util.Log;

import com.androidavanzado.bookingaitor.beans.Habitacion;
import com.androidavanzado.bookingaitor.habitacion.detailsHabitacion.contract.DetailsHabitacionContract;
import com.androidavanzado.bookingaitor.habitacion.findByHotel.view.ListHabitacionByHotelActivity;
import com.androidavanzado.bookingaitor.habitacion.findByPrecioAsc.view.ListHabitacionByPrecioAscActivity;
import com.androidavanzado.bookingaitor.habitacion.findByPrecioDesc.view.ListHabitacionByPrecioDescActivity;
import com.androidavanzado.bookingaitor.utils.Post;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailsHabitacionModel implements DetailsHabitacionContract.Model {
    int idHabitacion;
    private ArrayList<Habitacion> detailsHabitacionArrayList;
    OnDetailsHabitacionListener onDetailsHabitacionListener;

    @Override
    public void getHabitacionLS(OnDetailsHabitacionListener onDetailsHabitacionListener) {
        this.onDetailsHabitacionListener = onDetailsHabitacionListener;
        DetailsHabitacionAsyncTask detailsHabitacionAsyncTask = new DetailsHabitacionAsyncTask();

        if (ListHabitacionByHotelActivity.idHabitacion != 0){
            this.idHabitacion = ListHabitacionByHotelActivity.idHabitacion;
        } else if (ListHabitacionByPrecioAscActivity.idHabitacion != 0){
            this.idHabitacion = ListHabitacionByPrecioAscActivity.idHabitacion;
        } else if (ListHabitacionByPrecioDescActivity.idHabitacion != 0){
            this.idHabitacion = ListHabitacionByPrecioDescActivity.idHabitacion;
        }

        detailsHabitacionAsyncTask.execute("http://192.168.1.68:8080/BookingAitor/Controller?ACTION=HABITACION&QUERY=FIND_BY_ID&ID=" + this.idHabitacion);

        ListHabitacionByHotelActivity.idHabitacion = 0;
        ListHabitacionByPrecioDescActivity.idHabitacion = 0;
        ListHabitacionByPrecioAscActivity.idHabitacion = 0;
    }

    class DetailsHabitacionAsyncTask extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            String url_select = strings[0];
            try{
                Post post = new Post();
                JSONObject objecthabitacionDetails = post.getServerDataGetObject(url_select);
                JSONArray jsonDetailsList = new JSONArray();
                jsonDetailsList.put(objecthabitacionDetails);
                detailsHabitacionArrayList = Habitacion.getArrayListFromJSON(jsonDetailsList);
            } catch (Exception e){
                Log.e("log_tag", "Error in http connection " + e.toString());
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean res) {
            if(res){
                onDetailsHabitacionListener.onResolve(detailsHabitacionArrayList);
            }else{
                onDetailsHabitacionListener.onReject("Error");
            }
        }
    }
}
