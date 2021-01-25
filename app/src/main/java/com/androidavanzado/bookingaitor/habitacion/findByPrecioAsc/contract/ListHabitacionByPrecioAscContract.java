package com.androidavanzado.bookingaitor.habitacion.findByPrecioAsc.contract;

import com.androidavanzado.bookingaitor.beans.Habitacion;

import java.util.ArrayList;

public interface ListHabitacionByPrecioAscContract {
    interface Model{
        interface OnListPrecioAscListener{
            void onResolve(ArrayList<Habitacion> habitacionArrayList);
            void onReject(String message);
        }
        void listHabitacionPrecioAsc(OnListPrecioAscListener onListPrecioAscListener);
    }

    interface Presenter{
        void getHabitacionPrecioAsc();
    }

    interface View{
        void onSuccess(ArrayList<Habitacion> habitacionArrayList);
        void onFailure(String error);
    }
}
