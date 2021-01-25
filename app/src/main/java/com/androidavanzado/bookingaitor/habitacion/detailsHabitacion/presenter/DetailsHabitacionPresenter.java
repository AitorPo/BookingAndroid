package com.androidavanzado.bookingaitor.habitacion.detailsHabitacion.presenter;

import com.androidavanzado.bookingaitor.beans.Habitacion;
import com.androidavanzado.bookingaitor.habitacion.detailsHabitacion.contract.DetailsHabitacionContract;
import com.androidavanzado.bookingaitor.habitacion.detailsHabitacion.model.DetailsHabitacionModel;

import java.util.ArrayList;

public class DetailsHabitacionPresenter implements DetailsHabitacionContract.Presenter {
    private DetailsHabitacionModel model;
    private DetailsHabitacionContract.View view;

    public DetailsHabitacionPresenter(DetailsHabitacionContract.View view){
        model = new DetailsHabitacionModel();
        this.view = view;
    }

    @Override
    public void getDetailsHabitacion() {
        model.getHabitacionLS(new DetailsHabitacionContract.Model.OnDetailsHabitacionListener() {
            @Override
            public void onResolve(ArrayList<Habitacion> details) {
                view.onSuccess(details);
            }

            @Override
            public void onReject(String message) {
                view.onFailure("Error al traer los datos del Model");
            }
        });
    }
}
