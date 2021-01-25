package com.androidavanzado.bookingaitor.hotel.listHotel.findByReservas.contract;

import com.androidavanzado.bookingaitor.beans.Hotel;

import java.util.ArrayList;

public interface ListHotelByReservasContract {
    interface Model{
        interface OnListHotelByReservasListener{
            void onResolve(ArrayList<Hotel> hotelArrayList);
            void onReject(String message);
        }
        void getHotelesReservasLH(OnListHotelByReservasListener onListHotelByReservasListener);
    }

    interface Presenter{
        void getHotelesReservas();
    }

    interface View{
        void onSuccess(ArrayList<Hotel> hotelArrayList);
        void onFailure(String error);
    }
}
