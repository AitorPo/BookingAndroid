package com.androidavanzado.bookingaitor.habitacion.findByPrecioAsc.presenter;

import com.androidavanzado.bookingaitor.beans.Habitacion;
import com.androidavanzado.bookingaitor.habitacion.findByPrecioAsc.contract.ListHabitacionByPrecioAscContract;
import com.androidavanzado.bookingaitor.habitacion.findByPrecioAsc.model.ListHabitacionByPrecioAscModel;

import java.util.ArrayList;

public class ListHabitacionByPrecioAscPresenter implements ListHabitacionByPrecioAscContract.Presenter {
    private ListHabitacionByPrecioAscModel model;
    private ListHabitacionByPrecioAscContract.View view;

    public ListHabitacionByPrecioAscPresenter(ListHabitacionByPrecioAscContract.View view){
        model = new ListHabitacionByPrecioAscModel();
        this.view = view;
    }

    @Override
    public void getHabitacionPrecioAsc() {
        model.listHabitacionPrecioAsc(new ListHabitacionByPrecioAscContract.Model.OnListPrecioAscListener() {
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
