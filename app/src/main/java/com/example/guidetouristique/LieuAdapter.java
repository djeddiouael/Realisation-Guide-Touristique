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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LieuAdapter extends RecyclerView.Adapter<LieuAdapter.LieuViewHolder> {
    private Context context;
    private List<Lieu> lieux;

    public LieuAdapter(Context context, List<Lieu> lieux) {
        this.context = context;
        this.lieux = lieux;
    }

    @NonNull
    @Override
    public LieuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lieu, parent, false);
        return new LieuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LieuViewHolder holder, int position) {
        Lieu lieu = lieux.get(position);
        holder.nom.setText(lieu.getNom());
        holder.description.setText(lieu.getDescription());
        holder.image.setImageResource(lieu.getImageResourceId());

        // Action bouton Localisation
        holder.btnLocation.setOnClickListener(v -> openLocation(lieu.getAdresse()));
    }

    @Override
    public int getItemCount() {
        return lieux.size();
    }

    public static class LieuViewHolder extends RecyclerView.ViewHolder {
        TextView nom, description;
        ImageView image;
        Button btnCall, btnEmail, btnLocation;

        public LieuViewHolder(@NonNull View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.nomLieu);
            description = itemView.findViewById(R.id.descriptionLieu);
            image = itemView.findViewById(R.id.imageLieu);
            btnCall = itemView.findViewById(R.id.btnCall);
            btnEmail = itemView.findViewById(R.id.btnEmail);
            btnLocation = itemView.findViewById(R.id.btnLocation);
        }
    }



    private void openLocation(String adresse) {
        // Utilisation de l'adresse dans Google Maps
        String uri = "https://www.google.com/maps/search/?api=1&query=" + Uri.encode(adresse);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        context.startActivity(intent);
    }
}
