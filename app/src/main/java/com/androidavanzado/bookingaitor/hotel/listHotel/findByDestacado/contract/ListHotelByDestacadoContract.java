package com.androidavanzado.bookingaitor.hotel.listHotel.findByDestacado.contract;

import com.androidavanzado.bookingaitor.beans.Hotel;

import java.util.ArrayList;

public interface ListHotelByDestacadoContract {
    interface Model{
        interface OnListHotelByDestacadoListener{
            void onResolve(ArrayList<Hotel> hotelArrayList);
            void onReject(String message);
        }
        void getDestacadosLH(OnListHotelByDestacadoListener onListHotelByDestacadoListener);
    }

    interface Presenter{
        void getHotelesDestacados();
    }

    interface View{
        void onSuccess(ArrayList<Hotel> hotelArrayList);
        void onFailure(String error);
    }
}
