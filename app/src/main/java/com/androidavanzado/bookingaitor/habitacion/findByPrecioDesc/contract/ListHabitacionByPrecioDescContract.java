package com.androidavanzado.bookingaitor.habitacion.findByPrecioDesc.contract;

import com.androidavanzado.bookingaitor.beans.Habitacion;

import java.util.ArrayList;

public interface ListHabitacionByPrecioDescContract {
    interface Model{
        interface OnListPrecioDescListener {
            void onResolve(ArrayList<Habitacion> habitacionArrayList);
            void onReject(String message);
        }
        void listHabitacionPrecioDesc(OnListPrecioDescListener onListPrecioDescListener);
    }

    interface Presenter{
        void getHabitacionPrecioDesc();
    }

    interface View{
        void onSuccess(ArrayList<Habitacion> habitacionArrayList);
        void onFailure(String error);
    }
}
