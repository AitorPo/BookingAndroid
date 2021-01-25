package com.androidavanzado.bookingaitor.hotel.listHotel.findByReservas.presenter;

import com.androidavanzado.bookingaitor.beans.Hotel;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByReservas.contract.ListHotelByReservasContract;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByReservas.model.ListHotelByReservasModel;

import java.util.ArrayList;

public class ListHotelByReservasPresenter implements ListHotelByReservasContract.Presenter {
    private ListHotelByReservasModel model;
    private ListHotelByReservasContract.View view;

    public ListHotelByReservasPresenter(ListHotelByReservasContract.View view){
        model = new ListHotelByReservasModel();
        this.view = view;
    }

    @Override
    public void getHotelesReservas() {
        model.getHotelesReservasLH(new ListHotelByReservasContract.Model.OnListHotelByReservasListener() {
            @Override
            public void onResolve(ArrayList<Hotel> hotelArrayList) {
                view.onSuccess(hotelArrayList);
            }

            @Override
            public void onReject(String message) {
                view.onFailure("Error al traer los datos del Model");
            }
        });
    }
}
