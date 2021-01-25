package com.androidavanzado.bookingaitor.ciudad.listCiudad.findAll.contract;

import com.androidavanzado.bookingaitor.beans.Ciudad;

import java.util.ArrayList;

public interface ListCiudadContract {
    interface Model{
        interface OnListCiudadModelListener{
            void onResolve(ArrayList<Ciudad> ciudadArrayList);
            void onReject (String message);
        }
        void getListCiudadLH(OnListCiudadModelListener onListCiudadModelListener);
    }

    interface Presenter{
        void getCiudades();
    }

    interface View{
        void onSuccess(ArrayList<Ciudad> ciudadArrayList);
        void onFailure(String error);
    }
}
