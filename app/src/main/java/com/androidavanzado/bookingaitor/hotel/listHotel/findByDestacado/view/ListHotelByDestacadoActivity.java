package com.androidavanzado.bookingaitor.hotel.listHotel.findByDestacado.view;

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
import com.androidavanzado.bookingaitor.hotel.listHotel.findByDestacado.contract.ListHotelByDestacadoContract;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByDestacado.presenter.ListHotelByDestacadoPresenter;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByPuntuacion.view.ListHotelByPuntuacionActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByReservas.view.ListHotelByReservasActivity;

import java.util.ArrayList;

public class ListHotelByDestacadoActivity extends AppCompatActivity implements ListHotelByDestacadoContract.View {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ListHotelAdapter adapter;
    private ListHotelByDestacadoPresenter presenter;

    public static int idHotel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hotel);
        Toolbar toolbar = findViewById(R.id.tbFilter);
        setSupportActionBar(toolbar);
        //Flecha de "atrás"
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Esconde título de la app en la toolbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        presenter = new ListHotelByDestacadoPresenter(this);
        presenter.getHotelesDestacados();

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
        if (id == R.id.destacados){
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
        } else if (id == R.id.rankingReservas){
            Intent intent = new Intent(this, ListHotelByReservasActivity.class);
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
        adapter = new ListHotelAdapter(hotelArrayList, this, (idHotelCard, adapterPosition) -> {
            Intent intent = new Intent(ListHotelByDestacadoActivity.this, DetailsHotelActivity.class);
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
