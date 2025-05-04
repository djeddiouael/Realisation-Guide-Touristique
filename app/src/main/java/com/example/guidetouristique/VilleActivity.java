package com.example.guidetouristique;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class VilleActivity extends AppCompatActivity {

    private TextView tvNomVille, tvDescription;
    private ImageView ivVille;
    private RecyclerView recyclerViewLieux, recyclerViewRestaurants, recyclerViewHotels;
    private List<Lieu> lieux;
    private List<Restaurant> restaurants;
    private List<Hotel> hotels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ville);

        // Récupération des éléments UI
        tvNomVille = findViewById(R.id.tvNomVille);
        tvDescription = findViewById(R.id.tvDescription);
        ivVille = findViewById(R.id.ivVille);

        recyclerViewLieux = findViewById(R.id.recyclerViewLieux);
        recyclerViewRestaurants = findViewById(R.id.recyclerViewRestaurants);
        recyclerViewHotels = findViewById(R.id.recyclerViewHotels);

        recyclerViewLieux.setNestedScrollingEnabled(false);
        recyclerViewRestaurants.setNestedScrollingEnabled(false);
        recyclerViewHotels.setNestedScrollingEnabled(false);

        // Récupération de l'identifiant de la ville (ex: "alger")
        Intent intent = getIntent();
        String villeId = intent.getStringExtra("ville_id");

        if (villeId != null) {
            // Récupérer les chaînes localisées dynamiquement
            int nameRes = getResources().getIdentifier("nom_" + villeId, "string", getPackageName());
            int descRes = getResources().getIdentifier("desc_" + villeId, "string", getPackageName());

            // Affichage du nom et de la description
            tvNomVille.setText(nameRes != 0 ? getString(nameRes) : villeId);
            tvDescription.setText(descRes != 0 ? getString(descRes) : "");

            // Image dynamique
            int imageResId = getResources().getIdentifier(villeId, "drawable", getPackageName());
            if (imageResId != 0) {
                ivVille.setImageResource(imageResId);
            }

            // Initialiser les données de la ville
            initData(villeId);

            // Adapter les listes
            recyclerViewLieux.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewLieux.setAdapter(new LieuAdapter(this, lieux));

            recyclerViewRestaurants.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewRestaurants.setAdapter(new RestaurantAdapter(this, restaurants));

            recyclerViewHotels.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewHotels.setAdapter(new HotelAdapter(this, hotels));
        }
    }

    private void initData(String villeId) {
        lieux = new ArrayList<>();
        restaurants = new ArrayList<>();
        hotels = new ArrayList<>();

        switch (villeId) {
            case "alger":
                lieux.add(new Lieu(getString(R.string.lieu_casbah), getString(R.string.desc_casbah), R.drawable.casbah, "La Casbah, Alger"));
                lieux.add(new Lieu(getString(R.string.lieu_jardin), getString(R.string.desc_jardin), R.drawable.jardin_essai, "Jardin d'Essai, Alger"));

                restaurants.add(new Restaurant(getString(R.string.rest_djenina), getString(R.string.desc_djenina), R.drawable.el_djenina, "eldjenina@gmail.com", "0777888999", "Restaurant El Djenina, Alger"));
                restaurants.add(new Restaurant(getString(R.string.rest_le_tantra), getString(R.string.desc_le_tantra), R.drawable.tantra, "tantra@gmail.com", "0777888999", "Restaurant Le Tantra, Alger"));

                hotels.add(new Hotel(getString(R.string.hotel_sofitel), getString(R.string.desc_sofitel), R.drawable.sofitel, "sofitel@gmail.com", "0777888999", "Rue Mohamed V, Alger"));
                hotels.add(new Hotel(getString(R.string.hotel_aurassi), getString(R.string.desc_aurassi), R.drawable.aurassi, "aurassi@gmail.com", "0666777888", "2 Boulevard Frantz Fanon, Alger"));
                break;

            case "oran":
                lieux.add(new Lieu(getString(R.string.lieu_santa_cruz), getString(R.string.desc_santa_cruz), R.drawable.santa_cruz ,"Fort Santa Cruz ,Oran"));
                lieux.add(new Lieu(getString(R.string.lieu_andalouses), getString(R.string.desc_andalouses), R.drawable.les_andalouses ,"Plage Les Andalouses ,Oran"));

                restaurants.add(new Restaurant(getString(R.string.rest_meridien), getString(R.string.desc_meridien), R.drawable.le_meridien ,"lemerdien@gmail.com", "0777888999", "Restaurant Le Méridien ,Oran" ));
                restaurants.add(new Restaurant(getString(R.string.rest_comete), getString(R.string.desc_comete),  R.drawable.la_comete ,"lacomete@gmail.com", "0777888999", "Restaurant La Comete ,Oran"));

                hotels.add(new Hotel(getString(R.string.hotel_royal), getString(R.string.desc_royal), R.drawable.royal_hotel, "RoyalHotel@gmail.com", "0777888999", "Royal Hotel ,Oran"));
                hotels.add(new Hotel(getString(R.string.hotel_ibis), getString(R.string.desc_ibis), R.drawable.ibis_hotel, "IbisHotel@gmail.com", "0666777888", "Ibis Hotel ,Oran"));



                break;

            case "tlemcen":
                lieux.add(new Lieu(getString(R.string.lieu_sidi_boumediene), getString(R.string.desc_sidi_boumediene), R.drawable.sidi_boumediene ,"Mosquée Sidi Boumediene ,Tlemcen"));
                lieux.add(new Lieu(getString(R.string.lieu_el_ourit), getString(R.string.desc_el_ourit), R.drawable.cascade_el_ourit ,"Cascade El Ourit ,Tlemcen"));

                restaurants.add(new Restaurant(getString(R.string.rest_dar_el_qadi), getString(R.string.desc_dar_el_qadi), R.drawable.dar_el_qadi ,"DarElQadi@gmail.com", "0777888999", "Restaurant Dar El Qadi ,Tlemcen"));
                restaurants.add(new Restaurant(getString(R.string.rest_granada), getString(R.string.desc_granada), R.drawable.granada ,"Granada@gmail.com", "0777888999", "Restaurant Granada ,Tlemcen"));

                hotels.add(new Hotel(getString(R.string.hotel_renaissance), getString(R.string.desc_renaissance), R.drawable.renaissance_hotel, "RenaissanceHotel@gmail.com", "0777888999", "Renaissance Hotel ,Tlemcen"));
                hotels.add(new Hotel(getString(R.string.hotel_stambouli), getString(R.string.desc_stambouli), R.drawable.stambouli_hotel, "StambouliHotel@gmail.com", "0666777888", "Hotel Stambouli ,Tlemcen"));


                break;
            case "constantine":
                lieux.add(new Lieu(getString(R.string.lieu_sidi_mcid), getString(R.string.desc_sidi_mcid), R.drawable.sidi_mcid ,"Pont Sidi M'Cid ,Constantine"));
                lieux.add(new Lieu(getString(R.string.lieu_palais_ahmed), getString(R.string.desc_palais_ahmed), R.drawable.palais_ahmed_bey ,"Palais Ahmed Bey ,Constantine"));

                restaurants.add(new Restaurant(getString(R.string.rest_amandine), getString(R.string.desc_amandine), R.drawable.lamandine ,"lamandine@gmail.com", "0777888999", "Restaurant L'amandine ,Constantine"));
                restaurants.add(new Restaurant(getString(R.string.rest_el_bey), getString(R.string.desc_el_bey), R.drawable.el_bey_restaurant ,"ElBeyrRstaurant@gmail.com", "0777888999", "Restaurant El Bey ,Constantine"));

                hotels.add(new Hotel(getString(R.string.hotel_marriott), getString(R.string.desc_marriott), R.drawable.marriot_hotel, "MarriotHotel@gmail.com", "0777888999", "Marriott Hotel ,Constantine"));
                hotels.add(new Hotel(getString(R.string.hotel_cirta), getString(R.string.desc_cirta), R.drawable.cirta_hotel, "CirtaHotel@gmail.com", "0666777888", "Cirta Hotel ,Constantine"));


                break;

            case "ghardaia":
                lieux.add(new Lieu(getString(R.string.lieu_ksar), getString(R.string.desc_ksar), R.drawable.ksar_ghardaia ,"Ksar de Ghardaïa"));
                lieux.add(new Lieu(getString(R.string.lieu_tafilelt), getString(R.string.desc_tafilelt), R.drawable.tafilelt_place ,"Tafielt, Ghardaïa"));

                restaurants.add(new Restaurant(getString(R.string.rest_rym), getString(R.string.desc_rym), R.drawable.le_rym ,"LeRym@gmail.com", "0777888999", "Restaurant Le Rym"));
                restaurants.add(new Restaurant(getString(R.string.rest_ksar), getString(R.string.desc_ksar_rest), R.drawable.dar_el_ksar ,"DarElKsar@gmail.com", "0777888999", "Restaurant Dar El Ksar"));

                hotels.add(new Hotel(getString(R.string.hotel_mzab), getString(R.string.desc_mzab),  R.drawable.mzab ,"HotelMzab@gmail.com", "0777888999", "Hotel Mzab ,Ghardaïa"));
                hotels.add(new Hotel(getString(R.string.hotel_belvedere), getString(R.string.desc_belvedere), R.drawable.le_belvedere ,"LeBelvedereHotel@gmail.com", "0777888999", "Hotel Le Belvédère "));

                break;

            case "timimoun":
                lieux.add(new Lieu(getString(R.string.lieu_sebkha), getString(R.string.desc_sebkha), R.drawable.sebkha_timimoun ,"Sebkha de Timimoun"));
                lieux.add(new Lieu(getString(R.string.lieu_palmeraie), getString(R.string.desc_palmeraie), R.drawable.palmerai_timimoun ,"Palmeraie de Timimoun"));

                restaurants.add(new Restaurant(getString(R.string.rest_auberge), getString(R.string.desc_auberge), R.drawable.auberge_gourara ,"RestaurantAuberge@gmail.com", "0777888999", "Restaurant Auberge du Gourara"));
                restaurants.add(new Restaurant(getString(R.string.rest_desert_rouge), getString(R.string.desc_desert_rouge), R.drawable.desert_rouge ,"DesertRouge@gmail.com", "0777888999", "Resataurant Le Désert Rouge"));

                hotels.add(new Hotel(getString(R.string.hotel_gourara), getString(R.string.desc_gourara), R.drawable.gourara ,"GouraraHotel@gmail.com", "0777888999", "Hotel Gourara"));
                hotels.add(new Hotel(getString(R.string.hotel_dar_agham), getString(R.string.desc_dar_agham),  R.drawable.dar_agham ,"DarAghamHotel@gmail.com", "0777888999", "Hotel Dar Agham"));

                break;
        }
    }
}
