package com.androidavanzado.bookingaitor.usuario;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.androidavanzado.bookingaitor.beans.Usuario;
import com.androidavanzado.bookingaitor.utils.Post;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginModel implements LoginContract.Model{
    private OnLoginListener onLoginListener;
    private ArrayList<Usuario> usuarios;

    @Override
    public void getLogin(OnLoginListener onLoginListener, Usuario usuario) {
        this.onLoginListener = onLoginListener;
        HashMap<String,String> param = new HashMap<>();
        param.put("ACTION", "USUARIO");
        param.put("QUERY", "LOGIN");
        param.put("EMAIL", usuario.getEmail());
        param.put("PASSWORD", usuario.getPassword());
        LoginAsyncTask loginAsyncTask = new LoginAsyncTask(param);
        loginAsyncTask.execute("http://192.168.1.68:8080/BookingAitor/Controller");
    }


    class LoginAsyncTask extends AsyncTask<String, Integer, Boolean>{
        private HashMap<String, String> parametros = null;
        public LoginAsyncTask(HashMap<String, String> parametros){
            super();
            this.parametros = parametros;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            String select = strings[0];
            try{
                Post post = new Post();
                JSONArray result = post.getServerDataPost(parametros, select);
                //JSONObject jsonObject = post.getServerDataGetObject("http://192.168.1.68:8080/BookingAitor/Controller?ACTION=USUARIO&QUERY=LOGIN&EMAIL=hola@hola.com&PASSWORD=asd" );
                usuarios = Usuario.getArrayListFromJSON(result);
            } catch (Exception e){
                Log.e("log_tag", "Error in http connection " + e.toString());
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean res) {
            if (res){
                if (usuarios == null){
                   return;
                } else {
                    onLoginListener.onResolve(usuarios.get(0));
                }
            } else{
                onLoginListener.onReject("Error al loguear");
            }
        }
    }
}
