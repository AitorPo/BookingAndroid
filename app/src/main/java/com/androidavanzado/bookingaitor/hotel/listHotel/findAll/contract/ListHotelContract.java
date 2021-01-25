package com.androidavanzado.bookingaitor.hotel.listHotel.findAll.contract;

import com.androidavanzado.bookingaitor.beans.Hotel;

import java.util.ArrayList;

public interface ListHotelContract {
    interface Model{

            interface OnListHotelListener{
                void onResolve(ArrayList<Hotel> hoteles);
                void onReject(String message);
            }
        void getHotelesLH(OnListHotelListener onListHotelListener);
    }

    interface Presenter{
        void getListHotel();
    }

    interface View{
        void onSuccess(ArrayList<Hotel> hoteles);
        void onFailure(String message);
    }
}
