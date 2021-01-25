package com.androidavanzado.bookingaitor.hotel.detailsHotel.view;

import android.content.Intent;
import android.os.Bundle;

import com.androidavanzado.bookingaitor.beans.Hotel;
import com.androidavanzado.bookingaitor.ciudad.listCiudad.findAll.view.ListCiudadActivity;
import com.androidavanzado.bookingaitor.habitacion.findByHotel.view.ListHabitacionByHotelActivity;
import com.androidavanzado.bookingaitor.habitacion.findByPrecioAsc.view.ListHabitacionByPrecioAscActivity;
import com.androidavanzado.bookingaitor.habitacion.findByPrecioDesc.view.ListHabitacionByPrecioDescActivity;
import com.androidavanzado.bookingaitor.hotel.detailsHotel.contract.DetailsHotelContract;
import com.androidavanzado.bookingaitor.hotel.detailsHotel.presenter.DetailsHotelPresenter;
import com.androidavanzado.bookingaitor.hotel.listHotel.findAll.view.ListHotelActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidavanzado.bookingaitor.R;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByDestacado.view.ListHotelByDestacadoActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByPuntuacion.view.ListHotelByPuntuacionActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByReservas.view.ListHotelByReservasActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DetailsHotelActivity extends AppCompatActivity implements DetailsHotelContract.View {
    private DetailsHotelPresenter presenter;
    private ImageView ivHotel;
    private TextView tvNombre, tvPuntuacionCount, tvLink, tvDireccion,
            tvReservasCount, tvNumHabitacionsCount, tvDescripcion;
    public static int idHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_item_details);
        Toolbar toolbar = findViewById(R.id.tbDetails);
        setSupportActionBar(toolbar);
        //Aparece la flecha de "atrás"
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Desaparece el título de la app
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        presenter = new DetailsHotelPresenter(this);
        presenter.getDetailsHotel();

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // Esconde la barra de navegación inferior por defecto de Android
                        //| View.SYSTEM_UI_FLAG_FULLSCREEN // Esconde la barra de estado (nv de batería, cobertura...)
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        //TODO intents hacia los activity correspondientes
        if (id == R.id.findAllHoteles){
            Intent intent = new Intent(this, ListHotelActivity.class);
            startActivity(intent);
        } else if (id == R.id.findAllByPuntuacion){
            Intent intent = new Intent(this, ListHotelByPuntuacionActivity.class);
            startActivity(intent);
        } else if (id == R.id.orderByPrecioDesc){
            Intent intent = new Intent(this, ListHabitacionByPrecioDescActivity.class);
            startActivity(intent);
        } else if (id == R.id.orderByPrecioAsc){
            Intent intent = new Intent(this, ListHabitacionByPrecioAscActivity.class);
            startActivity(intent);
        } else if (id == R.id.destacados){
            Intent intent = new Intent(this, ListHotelByDestacadoActivity.class);
            startActivity(intent);
        } else if (id == R.id.rankingReservas){
            Intent intent = new Intent(this, ListHotelByReservasActivity.class);
            startActivity(intent);
        } else if (id == R.id.ciudades){
            Intent intent = new Intent(this, ListCiudadActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(ArrayList<Hotel> details) {
        tvNombre = this.findViewById(R.id.tvNombre);
        tvPuntuacionCount = this.findViewById(R.id.tvPuntuacionCount);
        tvDireccion = this.findViewById(R.id.tvDireccion);
        tvReservasCount = this.findViewById(R.id.tvReservasCount);
        tvNumHabitacionsCount = this.findViewById(R.id.tvNumHabitacionsCount);
        tvDescripcion = this.findViewById(R.id.tvDescripcion);
        ivHotel = this.findViewById(R.id.ivHotel);
        tvLink = this.findViewById(R.id.tvLink);




        for (Hotel hotel : details){

            tvNombre.setText(hotel.getNombre());
            tvPuntuacionCount.setText(String.valueOf(hotel.getPuntuacion()));
            String urlImagen = "http://192.168.1.68:8080/BookingAitor/imagenes/hotel/" + hotel.getFoto() + ".jpg";
            Glide.with(this).load(urlImagen)
                    .centerInside()
                    .centerCrop()
                    .into(ivHotel);

            tvDireccion.setText(hotel.getDireccion());
            tvReservasCount.setText(String.valueOf(hotel.getNumReservas()));
            tvNumHabitacionsCount.setText(String.valueOf(hotel.getNumHabitaciones()));
            tvDescripcion.setText(hotel.getDescripcion());
            tvLink.setOnClickListener(v -> verTodasHabitaciones(hotel));
        }


    }

    public void verTodasHabitaciones(Hotel hotel){
        Intent intent = new Intent(DetailsHotelActivity.this, ListHabitacionByHotelActivity.class);
        idHotel = hotel.getIdHotel();
        startActivity(intent);
    }


    @Override
    public void onFailure(String error) {

    }
}