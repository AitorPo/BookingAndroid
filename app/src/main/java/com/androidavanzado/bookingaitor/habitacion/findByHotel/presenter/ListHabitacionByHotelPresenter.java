package com.androidavanzado.bookingaitor.habitacion.findByHotel.presenter;

import com.androidavanzado.bookingaitor.beans.Habitacion;
import com.androidavanzado.bookingaitor.habitacion.findByHotel.contract.ListHabitacionByHotelContract;
import com.androidavanzado.bookingaitor.habitacion.findByHotel.model.ListHabitacionByHotelModel;

import java.util.ArrayList;

public class ListHabitacionByHotelPresenter implements ListHabitacionByHotelContract.Presenter {
    private ListHabitacionByHotelModel model;
    private ListHabitacionByHotelContract.View view;

    public ListHabitacionByHotelPresenter(ListHabitacionByHotelContract.View view){
        model = new ListHabitacionByHotelModel();
        this.view = view;
    }

    @Override
    public void getHabitacionList() {
        model.getHabitacionListLH(new ListHabitacionByHotelContract.Model.OnListHabitacionByHotelListener() {
            @Override
            public void onResolve(ArrayList<Habitacion> habitacionArrayList) {
                view.onSuccess(habitacionArrayList);
            }

            @Override
            public void onReject(String message) {
                view.onFailure("Error al traer los datos del Model");
            }
        });
    }
}
