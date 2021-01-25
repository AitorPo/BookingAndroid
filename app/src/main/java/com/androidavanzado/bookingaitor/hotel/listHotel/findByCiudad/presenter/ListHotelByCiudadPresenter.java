package com.androidavanzado.bookingaitor.hotel.listHotel.findByCiudad.presenter;

import com.androidavanzado.bookingaitor.beans.Hotel;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByCiudad.contract.ListHotelByCiudadContract;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByCiudad.model.ListHotelByCiudadModel;

import java.util.ArrayList;

public class ListHotelByCiudadPresenter implements ListHotelByCiudadContract.Presenter {
    ListHotelByCiudadModel model;
    ListHotelByCiudadContract.View view;

    public ListHotelByCiudadPresenter(ListHotelByCiudadContract.View view){
        this.view = view;
        model = new ListHotelByCiudadModel();
    }

    @Override
    public void getHotelByCiudad() {
        model.listHotelByCiudadLH(new ListHotelByCiudadContract.Model.OnListHotelByCiudadListener() {
            @Override
            public void onResolve(ArrayList<Hotel> hotelArrayList) {
                view.onSuccess(hotelArrayList);
            }

            @Override
            public void onReject(String message) {
                view.onFailure("Error al traer los datos desde el Model");
            }
        });
    }
}
