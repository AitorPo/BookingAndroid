package com.androidavanzado.bookingaitor.usuario;

import com.androidavanzado.bookingaitor.beans.Usuario;

public interface LoginContract {
    interface Model{
        interface OnLoginListener{
            void onResolve(Usuario usuario);
            void onReject(String error);
        }
        void getLogin(OnLoginListener onLoginListener, Usuario usuario);
    }

    interface Presenter{
        void doLoginPresenter(Usuario usuario);
    }

    interface View{
        void onSuccess(Usuario usuario);
        void onFailure(String error);
    }
}
