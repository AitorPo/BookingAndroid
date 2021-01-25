package com.androidavanzado.bookingaitor.hotel.listHotel.findByDestacado.presenter;

import com.androidavanzado.bookingaitor.beans.Hotel;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByDestacado.contract.ListHotelByDestacadoContract;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByDestacado.model.ListHotelByDestacadoModel;

import java.util.ArrayList;

public class ListHotelByDestacadoPresenter implements ListHotelByDestacadoContract.Presenter {
    private ListHotelByDestacadoModel model;
    private ListHotelByDestacadoContract.View view;

    public ListHotelByDestacadoPresenter(ListHotelByDestacadoContract.View view){
        this.view = view;
        this. model = new ListHotelByDestacadoModel();
    }

    @Override
    public void getHotelesDestacados() {
        model.getDestacadosLH(new ListHotelByDestacadoContract.Model.OnListHotelByDestacadoListener() {
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
