package com.androidavanzado.bookingaitor.hotel.listHotel.findByCiudad.contract;

import com.androidavanzado.bookingaitor.beans.Hotel;

import java.util.ArrayList;

public interface ListHotelByCiudadContract {
    interface Model {
        interface OnListHotelByCiudadListener{
            void onResolve(ArrayList<Hotel> hotelArrayList);
            void onReject(String message);
        }
        void listHotelByCiudadLH(OnListHotelByCiudadListener onListHotelByCiudadListener);
    }

    interface Presenter{
        void getHotelByCiudad();
    }

    interface View{
        void onSuccess(ArrayList<Hotel> hotelArrayList);
        void onFailure(String error);
    }
}
