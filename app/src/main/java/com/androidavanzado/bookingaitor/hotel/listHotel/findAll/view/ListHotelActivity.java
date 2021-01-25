package com.androidavanzado.bookingaitor.hotel.listHotel.findAll.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.androidavanzado.bookingaitor.R;
import com.androidavanzado.bookingaitor.beans.Hotel;
import com.androidavanzado.bookingaitor.ciudad.listCiudad.findAll.view.ListCiudadActivity;
import com.androidavanzado.bookingaitor.habitacion.findByPrecioAsc.view.ListHabitacionByPrecioAscActivity;
import com.androidavanzado.bookingaitor.habitacion.findByPrecioDesc.view.ListHabitacionByPrecioDescActivity;
import com.androidavanzado.bookingaitor.hotel.detailsHotel.view.DetailsHotelActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findAll.contract.ListHotelContract;
import com.androidavanzado.bookingaitor.hotel.listHotel.findAll.presenter.ListHotelPresenter;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByDestacado.view.ListHotelByDestacadoActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByPuntuacion.view.ListHotelByPuntuacionActivity;
import com.androidavanzado.bookingaitor.hotel.listHotel.findByReservas.view.ListHotelByReservasActivity;

import java.util.ArrayList;

public class ListHotelActivity extends AppCompatActivity implements ListHotelContract.View {
    private ListHotelPresenter presenter;
    private ListHotelAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    public static int idHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hotel);
        Toolbar toolbar = findViewById(R.id.tbFilter);
        setSupportActionBar(toolbar);
        //Aparece la flecha de "atrás"
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Desaparece el título de la app
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        presenter = new ListHotelPresenter(this);
        presenter.getListHotel();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

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
        if (id == R.id.findAllHoteles){
            return true;
        } else if (id == R.id.findAllByPuntuacion){
            Intent intent = new Intent(ListHotelActivity.this, ListHotelByPuntuacionActivity.class);
            startActivity(intent);
        } else if (id == R.id.orderByPrecioDesc){
            Intent intent = new Intent(ListHotelActivity.this, ListHabitacionByPrecioDescActivity.class);
            startActivity(intent);
        } else if (id == R.id.orderByPrecioAsc){
            Intent intent = new Intent(ListHotelActivity.this, ListHabitacionByPrecioAscActivity.class);
            startActivity(intent);
        } else if (id == R.id.destacados){
            Intent intent = new Intent(ListHotelActivity.this, ListHotelByDestacadoActivity.class);
            startActivity(intent);
        } else if (id == R.id.rankingReservas){
            Intent intent = new Intent(ListHotelActivity.this, ListHotelByReservasActivity.class);
            startActivity(intent);
        } else if (id == R.id.ciudades){
            Intent intent = new Intent(ListHotelActivity.this, ListCiudadActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(ArrayList<Hotel> hoteles) {


        adapter = new ListHotelAdapter(hoteles, this, new ListHotelAdapter.OnCardClickListener() {
            @Override
            public void onCardClick(int id, int position) {
                  //TODO implementar intent para los detalles del hotel clickado
                Intent intent = new Intent(ListHotelActivity.this, DetailsHotelActivity.class);
                idHotel = hoteles.get(position).getIdHotel();
                intent.getIntExtra("HOTEL_ID", idHotel);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFailure(String message) {

    }
}