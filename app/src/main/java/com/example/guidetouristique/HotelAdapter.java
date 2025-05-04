package com.example.guidetouristique;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {
    private Context context;
    private List<Hotel> hotels;

    public HotelAdapter(Context context, List<Hotel> hotels) {
        this.context = context;
        this.hotels = hotels;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hotel, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        Hotel hotel = hotels.get(position);
        holder.nom.setText(hotel.getNom());
        holder.etoiles.setText(hotel.getEtoiles());
        holder.image.setImageResource(hotel.getImageResourceId());

        // Actions des boutons
        holder.btnCall.setOnClickListener(v -> callContact(hotel.getTelephone()));
        holder.btnEmail.setOnClickListener(v -> sendEmail(hotel.getEmail()));
        holder.btnLocation.setOnClickListener(v -> openLocation(hotel.getAdresse()));
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public static class HotelViewHolder extends RecyclerView.ViewHolder {
        TextView nom, etoiles;
        ImageView image;
        Button btnCall, btnEmail, btnLocation;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.nomHotel);
            etoiles = itemView.findViewById(R.id.etoilesHotel);
            image = itemView.findViewById(R.id.imageHotel);
            btnCall = itemView.findViewById(R.id.btnCall);
            btnEmail = itemView.findViewById(R.id.btnEmail);
            btnLocation = itemView.findViewById(R.id.btnLocation);
        }
    }

    private void callContact(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        context.startActivity(intent);
    }

    private void sendEmail(String email) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
        context.startActivity(intent);
    }

    private void openLocation(String adresse) {
        // Utilisation de l'adresse dans Google Maps
        String uri = "https://www.google.com/maps/search/?api=1&query=" + Uri.encode(adresse);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        context.startActivity(intent);
    }
}
