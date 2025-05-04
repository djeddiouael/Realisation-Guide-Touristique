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

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {
    private Context context;
    private List<Restaurant> restaurants;

    public RestaurantAdapter(Context context, List<Restaurant> restaurants) {
        this.context = context;
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_restaurant, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);
        holder.nom.setText(restaurant.getNom());
        holder.specialite.setText(restaurant.getSpecialite());
        holder.image.setImageResource(restaurant.getImageResourceId());

        holder.btnCall.setOnClickListener(v -> callContact(restaurant.getTelephone()));
        holder.btnEmail.setOnClickListener(v -> sendEmail(restaurant.getEmail()));
        holder.btnLocation.setOnClickListener(v -> openLocation(restaurant.getAdresse()));
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        TextView nom, specialite;
        ImageView image;
        Button btnCall, btnEmail, btnLocation;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.nomRestaurant);
            specialite = itemView.findViewById(R.id.specialiteRestaurant);
            image = itemView.findViewById(R.id.imageRestaurant);
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
