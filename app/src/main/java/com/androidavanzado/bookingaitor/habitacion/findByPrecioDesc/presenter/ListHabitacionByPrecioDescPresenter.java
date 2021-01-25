package com.androidavanzado.bookingaitor.habitacion.findByPrecioDesc.presenter;

import com.androidavanzado.bookingaitor.beans.Habitacion;
import com.androidavanzado.bookingaitor.habitacion.findByPrecioDesc.contract.ListHabitacionByPrecioDescContract;
import com.androidavanzado.bookingaitor.habitacion.findByPrecioDesc.model.ListHabitacionByPrecioDescModel;

import java.util.ArrayList;

public class ListHabitacionByPrecioDescPresenter implements ListHabitacionByPrecioDescContract.Presenter {
    private ListHabitacionByPrecioDescModel model;
    private ListHabitacionByPrecioDescContract.View view;

    public ListHabitacionByPrecioDescPresenter(ListHabitacionByPrecioDescContract.View view){
        model = new ListHabitacionByPrecioDescModel();
        this.view = view;
    }

    @Override
    public void getHabitacionPrecioDesc() {
        model.listHabitacionPrecioDesc(new ListHabitacionByPrecioDescContract.Model.OnListPrecioDescListener() {
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
