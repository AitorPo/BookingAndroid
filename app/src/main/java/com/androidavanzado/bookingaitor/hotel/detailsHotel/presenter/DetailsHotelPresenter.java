package com.androidavanzado.bookingaitor.hotel.detailsHotel.presenter;

import com.androidavanzado.bookingaitor.beans.Hotel;
import com.androidavanzado.bookingaitor.hotel.detailsHotel.contract.DetailsHotelContract;
import com.androidavanzado.bookingaitor.hotel.detailsHotel.model.DetailsHotelModel;

import java.util.ArrayList;

public class DetailsHotelPresenter implements DetailsHotelContract.Presenter {
    private DetailsHotelModel detailsHotelModel;
    private DetailsHotelContract.View view;

    public DetailsHotelPresenter(DetailsHotelContract.View view){
        this.view = view;
        this.detailsHotelModel = new DetailsHotelModel();
    }

    @Override
    public void getDetailsHotel() {
        detailsHotelModel.getHotelLS(new DetailsHotelContract.Model.OnDetailsHotelListener() {
            @Override
            public void onResolve(ArrayList<Hotel> details) {
                view.onSuccess(details);
            }

            @Override
            public void onReject(String message) {
                view.onFailure("Error al traer los datos desde el Presenter");
            }
        });
    }
}
