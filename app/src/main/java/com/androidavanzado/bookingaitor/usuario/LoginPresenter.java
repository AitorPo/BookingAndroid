package com.androidavanzado.bookingaitor.usuario;

import com.androidavanzado.bookingaitor.beans.Usuario;

public class LoginPresenter implements LoginContract.Presenter{
    private LoginContract.View view;
    private LoginModel model;

    public LoginPresenter(LoginContract.View view){
        model = new LoginModel();
        this.view = view;
    }

    @Override
    public void doLoginPresenter(Usuario usuario) {
        model .getLogin(new LoginContract.Model.OnLoginListener() {
            @Override
            public void onResolve(Usuario usuario) {
                view.onSuccess(usuario);
            }

            @Override
            public void onReject(String error) {
                view.onFailure("Error al traer los datos del Model");
            }
        }, usuario);
    }
}
