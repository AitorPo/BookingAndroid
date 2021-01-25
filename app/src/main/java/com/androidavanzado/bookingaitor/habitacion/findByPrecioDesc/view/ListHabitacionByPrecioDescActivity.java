package com.androidavanzado.bookingaitor.habitacion.findByPrecioDesc.view;

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
import com.androidavanzado.bookingaitor.beans.Habitacion;
import com.androidavanzado.bookingaitor.ciudad.listCiudad.findAll.view.ListCiudadActivity;
import com.androidavanzado.bookingaitor.habitacion.detailsHabitacion.view.DetailsHabitacionActivity;
import com.androidavanzado.bookingaitor.habitacion.findByPrecioAsc.view.ListHabitacionByPrecioAscActivity;
import com.androidavanzado.bookingaitor.habitacion.findByPrecioAsc.view.ListHabitacionByPrecioAscAdapter;
import com.androidavanzado.bookingaitor.habitacion.findByPrecioDesc.contract.ListHabitacionByPrecioDescContract;
import com.androidavanzado.bookingaitor.habitacion.findByPrecioDesc.presenter.ListHabitacionByPrecioDescPresenter;
import com.androidavanzado.bookingaitor.hotel.listHotel.findAll.view.ListHotelActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByDestacado.view.ListHotelByDestacadoActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByPuntuacion.view.ListHotelByPuntuacionActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByReservas.view.ListHotelByReservasActivity;

import java.util.ArrayList;

public class ListHabitacionByPrecioDescActivity extends AppCompatActivity implements ListHabitacionByPrecioDescContract.View {
    private RecyclerView recyclerViewHabitacion;
    private RecyclerView.LayoutManager layoutManagerHabitacion;
    private ListHabitacionByPrecioDescPresenter presenter;
    private ListHabitacionByPrecioAscAdapter adapter;
    public static int idHabitacion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_habitacion);
        Toolbar toolbar = findViewById(R.id.tbFilterHabitacion);
        setSupportActionBar(toolbar);
        //Aparece la flecha de "atrás"
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Desaparece el título de la app
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        presenter = new ListHabitacionByPrecioDescPresenter(this);
        presenter.getHabitacionPrecioDesc();

        recyclerViewHabitacion = findViewById(R.id.recyclerViewHabitacion);
        recyclerViewHabitacion.setHasFixedSize(true);

        layoutManagerHabitacion = new LinearLayoutManager(this);
        recyclerViewHabitacion.setLayoutManager(layoutManagerHabitacion);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        //| View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
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
        if (id == R.id.orderByPrecioDesc){
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
    public void onSuccess(ArrayList<Habitacion> habitacionArrayList) {
        adapter = new ListHabitacionByPrecioAscAdapter(habitacionArrayList, this, (id, position) -> {
            Intent intent = new Intent(this, DetailsHabitacionActivity.class);
            idHabitacion = habitacionArrayList.get(position).getIdHabitacion();
            startActivity(intent);
        });

        recyclerViewHabitacion.setAdapter(adapter);
    }

    @Override
    public void onFailure(String error) {

    }
}
