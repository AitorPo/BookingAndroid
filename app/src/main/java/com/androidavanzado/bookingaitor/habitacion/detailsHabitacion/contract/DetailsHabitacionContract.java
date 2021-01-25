package com.androidavanzado.bookingaitor.habitacion.detailsHabitacion.contract;

import com.androidavanzado.bookingaitor.beans.Habitacion;

import java.util.ArrayList;

public interface DetailsHabitacionContract {
    interface Model {
        interface OnDetailsHabitacionListener {
            void onResolve(ArrayList<Habitacion> details);
            void onReject(String message);
        }
        void getHabitacionLS(OnDetailsHabitacionListener onDetailsHotelListener);
    }

    interface Presenter{
        void getDetailsHabitacion();
    }

    interface View{
        void onSuccess(ArrayList<Habitacion> details);
        void onFailure(String error);
    }
}
