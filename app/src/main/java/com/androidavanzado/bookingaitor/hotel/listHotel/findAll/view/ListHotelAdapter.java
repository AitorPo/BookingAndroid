package com.androidavanzado.bookingaitor.hotel.listHotel.findAll.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.bookingaitor.R;
import com.androidavanzado.bookingaitor.beans.Hotel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListHotelAdapter extends RecyclerView.Adapter<ListHotelAdapter.ViewHolder> {
    ArrayList<Hotel> hotelArrayList;
    Context context;
    OnCardClickListener onCardClickListener;

    public ListHotelAdapter(ArrayList<Hotel> hotelArrayList, Context context, OnCardClickListener onCardClickListener){
        this.hotelArrayList = hotelArrayList;
        this.context = context;
        this.onCardClickListener = onCardClickListener;
    }

    @NonNull
    @Override
    public ListHotelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hotel_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListHotelAdapter.ViewHolder holder, int position) {
        holder.hotel = hotelArrayList.get(position);
        holder.bind(holder.hotel, onCardClickListener);
        holder.tvNombre.setText(holder.hotel.getNombre());
        holder.tvPuntuacion.setText(String.valueOf(holder.hotel.getPuntuacion()));

        String urlImagen = "http://192.168.1.68:8080/BookingAitor/imagenes/hotel/" + hotelArrayList.get(position).getFoto() + ".jpg";

        Glide.with(context).load(urlImagen)
                .clone()
                .centerCrop()
                .into(holder.ivFoto);

    }

    @Override
    public int getItemCount() {
        return hotelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvNombre;
        public final TextView tvPuntuacion;
        public final ImageView ivFoto;
        public final CardView cardViewHotel;
        public Hotel hotel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreHotel);
            tvPuntuacion = itemView.findViewById(R.id.tvNota);
            ivFoto = itemView.findViewById(R.id.ivFotoHabitacion);
            cardViewHotel = itemView.findViewById(R.id.cardViewHabitacion);
        }

        public void bind(Hotel hotel, final OnCardClickListener onCardClickListener){
            cardViewHotel.setOnClickListener(v -> onCardClickListener.onCardClick(hotel.getIdHotel(), getAdapterPosition()));
        }
    }
    public interface OnCardClickListener{
        void onCardClick(int id, int position);
    }
}
