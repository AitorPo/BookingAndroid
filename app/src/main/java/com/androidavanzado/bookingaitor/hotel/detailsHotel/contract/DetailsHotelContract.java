package com.androidavanzado.bookingaitor.hotel.detailsHotel.contract;

import com.androidavanzado.bookingaitor.beans.Hotel;

import java.util.ArrayList;

public interface DetailsHotelContract {
    interface Model {
        interface OnDetailsHotelListener {
            void onResolve(ArrayList<Hotel> details);
            void onReject(String message);
        }
        void getHotelLS(Model.OnDetailsHotelListener onDetailsHotelListener);
    }

    interface Presenter{
        void getDetailsHotel();
    }

    interface View{
        void onSuccess(ArrayList<Hotel> details);
        void onFailure(String error);
    }

}
