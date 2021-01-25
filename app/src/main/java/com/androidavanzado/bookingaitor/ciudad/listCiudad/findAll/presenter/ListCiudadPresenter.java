package com.androidavanzado.bookingaitor.ciudad.listCiudad.findAll.presenter;

import com.androidavanzado.bookingaitor.beans.Ciudad;
import com.androidavanzado.bookingaitor.ciudad.listCiudad.findAll.contract.ListCiudadContract;
import com.androidavanzado.bookingaitor.ciudad.listCiudad.findAll.model.ListCiudadModel;

import java.util.ArrayList;

public class ListCiudadPresenter implements ListCiudadContract.Presenter {
    private ListCiudadModel model;
    private ListCiudadContract.View view;

    public ListCiudadPresenter(ListCiudadContract.View view){
        model = new ListCiudadModel();
        this.view = view;
    }

    @Override
    public void getCiudades() {
        model.getListCiudadLH(new ListCiudadContract.Model.OnListCiudadModelListener() {
            @Override
            public void onResolve(ArrayList<Ciudad> ciudadArrayList) {
                view.onSuccess(ciudadArrayList);
            }

            @Override
            public void onReject(String message) {
                view.onFailure("Error al traer los datos desde el Model");
            }
        });
    }
}
