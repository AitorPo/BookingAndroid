package com.androidavanzado.bookingaitor.hotel.listHotel.findByPuntuacion.contract;

import com.androidavanzado.bookingaitor.beans.Hotel;

import java.util.ArrayList;

public interface ListHotelByPuntuacionContract {
    interface Model {
        interface OnListHotelByPuntuacionListener {
            void onResolve(ArrayList<Hotel> hotelArrayList);
            void onReject(String message);
        }
        void getListByPuntuacionLH(OnListHotelByPuntuacionListener onListHotelByPuntuacionListener);
    }

    interface Presenter{
        void getListPuntuacion();
    }

    interface View{
        void onSuccess(ArrayList<Hotel> hotelArrayList);
        void onFailure(String error);
    }
}
