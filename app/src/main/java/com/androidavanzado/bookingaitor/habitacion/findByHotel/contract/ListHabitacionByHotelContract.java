package com.androidavanzado.bookingaitor.habitacion.findByHotel.contract;

import com.androidavanzado.bookingaitor.beans.Habitacion;

import java.util.ArrayList;

public interface ListHabitacionByHotelContract {
    interface Model{
        interface OnListHabitacionByHotelListener{
            void onResolve(ArrayList<Habitacion> habitacionArrayList);
            void onReject(String message);
        }
        void getHabitacionListLH(OnListHabitacionByHotelListener onListHabitacionByHotelListener);
    }

    interface Presenter{
        void getHabitacionList();
    }

    interface View{
        void onSuccess(ArrayList<Habitacion> habitacionArrayList);
        void onFailure(String error);
    }
}
