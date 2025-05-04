package com.example.guidetouristique;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VilleAdapter extends RecyclerView.Adapter<VilleAdapter.VilleViewHolder> {
    private Context context;
    private List<Ville> villeList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Ville ville);
    }

    public VilleAdapter(Context context, List<Ville> villeList, OnItemClickListener listener) {
        this.context = context;
        this.villeList = villeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VilleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ville, parent, false);
        return new VilleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VilleViewHolder holder, int position) {
        Ville ville = villeList.get(position);

        // Traduire dynamiquement le nom de la wilaya depuis strings.xml
        String villeNameId = "ville_" + ville.getId(); // ex: ville_alger
        int resId = context.getResources().getIdentifier(villeNameId, "string", context.getPackageName());
        String translatedName = resId != 0 ? context.getString(resId) : ville.getId();

        holder.tvNom.setText(translatedName);
        holder.ivImage.setImageResource(ville.getImageResId());

        holder.itemView.setOnClickListener(v -> listener.onItemClick(ville));
    }

    @Override
    public int getItemCount() {
        return villeList.size();
    }

    static class VilleViewHolder extends RecyclerView.ViewHolder {
        TextView tvNom;
        ImageView ivImage;

        public VilleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNom = itemView.findViewById(R.id.tvNom);
            ivImage = itemView.findViewById(R.id.ivImage);
        }
    }
}
