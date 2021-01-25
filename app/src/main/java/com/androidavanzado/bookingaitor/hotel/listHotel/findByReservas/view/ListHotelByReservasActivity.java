package com.androidavanzado.bookingaitor.hotel.listHotel.findByReservas.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.bookingaitor.R;
import com.androidavanzado.bookingaitor.beans.Hotel;
import com.androidavanzado.bookingaitor.ciudad.listCiudad.findAll.view.ListCiudadActivity;
import com.androidavanzado.bookingaitor.habitacion.findByPrecioAsc.view.ListHabitacionByPrecioAscActivity;
import com.androidavanzado.bookingaitor.habitacion.findByPrecioDesc.view.ListHabitacionByPrecioDescActivity;
import com.androidavanzado.bookingaitor.hotel.detailsHotel.view.DetailsHotelActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findAll.view.ListHotelActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findAll.view.ListHotelAdapter;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByDestacado.view.ListHotelByDestacadoActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByPuntuacion.view.ListHotelByPuntuacionActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByReservas.contract.ListHotelByReservasContract;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByReservas.presenter.ListHotelByReservasPresenter;

import java.util.ArrayList;

public class ListHotelByReservasActivity extends AppCompatActivity implements ListHotelByReservasContract.View {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ListHotelAdapter adapter;
    private ListHotelByReservasPresenter presenter;

    public static int idHotel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hotel);
        Toolbar toolbar = findViewById(R.id.tbFilter);
        setSupportActionBar(toolbar);
        //Felcha de "atrás"
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Oculta el nombre de la app
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        presenter = new ListHotelByReservasPresenter(this);
        presenter.getHotelesReservas();

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // Esconde la barra de navegación inferior por defecto de Android
                        //| View.SYSTEM_UI_FLAG_FULLSCREEN // Esconde la barra de estado (nv de batería, cobertura...)
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
        );
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

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
        if (id == R.id.rankingReservas){
            return true;
        } else if (id == R.id.findAllByPuntuacion){
            Intent intent = new Intent(this, ListHotelByPuntuacionActivity.class);
            startActivity(intent);
        } else if (id == R.id.orderByPrecioAsc){
            Intent intent = new Intent(this, ListHabitacionByPrecioAscActivity.class);
            startActivity(intent);
        } else if (id == R.id.findAllHoteles){
            Intent intent = new Intent(this, ListHotelActivity.class);
            startActivity(intent);
        } else if (id == R.id.destacados){
            Intent intent = new Intent(this, ListHotelByDestacadoActivity.class);
            startActivity(intent);
        } else if (id == R.id.orderByPrecioDesc){
            Intent intent = new Intent(this, ListHabitacionByPrecioDescActivity.class);
            startActivity(intent);
        } else if (id == R.id.ciudades){
            Intent intent = new Intent(this, ListCiudadActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(ArrayList<Hotel> hotelArrayList) {
        adapter = new ListHotelAdapter(hotelArrayList, this, (idHotelReservas, adapterPosition) -> {
            Intent intent = new Intent(this, DetailsHotelActivity.class);
            idHotel = hotelArrayList.get(adapterPosition).getIdHotel();
            intent.getIntExtra("ID_HOTEL", idHotel);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFailure(String error) {

    }
}
