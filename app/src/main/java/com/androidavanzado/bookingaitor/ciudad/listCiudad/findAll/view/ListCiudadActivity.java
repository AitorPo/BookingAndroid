package com.androidavanzado.bookingaitor.ciudad.listCiudad.findAll.view;

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
import com.androidavanzado.bookingaitor.beans.Ciudad;
import com.androidavanzado.bookingaitor.ciudad.listCiudad.findAll.contract.ListCiudadContract;
import com.androidavanzado.bookingaitor.ciudad.listCiudad.findAll.presenter.ListCiudadPresenter;
import com.androidavanzado.bookingaitor.habitacion.findByPrecioAsc.view.ListHabitacionByPrecioAscActivity;
import com.androidavanzado.bookingaitor.habitacion.findByPrecioDesc.view.ListHabitacionByPrecioDescActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findAll.view.ListHotelActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByCiudad.view.ListHotelByCiudadActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByDestacado.view.ListHotelByDestacadoActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByPuntuacion.view.ListHotelByPuntuacionActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByReservas.view.ListHotelByReservasActivity;

import java.util.ArrayList;

public class ListCiudadActivity extends AppCompatActivity implements ListCiudadContract.View {
    private RecyclerView recyclerViewCiudad;
    private RecyclerView.LayoutManager layoutManagerCiudad;
    private ListCiudadAdapter adapter;
    private ListCiudadPresenter presenter;

    public static int idCiudad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ciudad);
        Toolbar toolbar = findViewById(R.id.tbFilterHabitacion);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        presenter = new ListCiudadPresenter(this);
        presenter.getCiudades();

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        //| View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
        );

        recyclerViewCiudad = findViewById(R.id.recyclerViewHabitacion);
        recyclerViewCiudad.setHasFixedSize(true);

        layoutManagerCiudad = new LinearLayoutManager(this);
        recyclerViewCiudad.setLayoutManager(layoutManagerCiudad);
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(ArrayList<Ciudad> ciudadArrayList) {
        adapter = new ListCiudadAdapter(ciudadArrayList, this, (id, adapterPosition) -> {
            Intent intent = new Intent(this, ListHotelByCiudadActivity.class);
            idCiudad = ciudadArrayList.get(adapterPosition).getIdCiudad();
            intent.getIntExtra("ID_CIUDAD", idCiudad);
            startActivity(intent);
        });
        recyclerViewCiudad.setAdapter(adapter);
    }

    @Override
    public void onFailure(String error) {

    }
}
