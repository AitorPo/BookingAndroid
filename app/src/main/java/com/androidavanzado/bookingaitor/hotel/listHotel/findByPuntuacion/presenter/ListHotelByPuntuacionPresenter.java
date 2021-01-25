package com.androidavanzado.bookingaitor.hotel.listHotel.findByPuntuacion.presenter;

import com.androidavanzado.bookingaitor.beans.Hotel;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByPuntuacion.contract.ListHotelByPuntuacionContract;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByPuntuacion.model.ListHotelByPuntuacionModel;

import java.util.ArrayList;

public class ListHotelByPuntuacionPresenter implements ListHotelByPuntuacionContract.Presenter {
    private ListHotelByPuntuacionModel listHotelByPuntuacionModel;
    private ListHotelByPuntuacionContract.View view;

    public ListHotelByPuntuacionPresenter(ListHotelByPuntuacionContract.View view){
        this.view = view;
        listHotelByPuntuacionModel = new ListHotelByPuntuacionModel();
    }
    @Override
    public void getListPuntuacion() {
        listHotelByPuntuacionModel.getListByPuntuacionLH(new ListHotelByPuntuacionContract.Model.OnListHotelByPuntuacionListener() {
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
