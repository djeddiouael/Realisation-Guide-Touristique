package com.example.guidetouristique;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private VilleAdapter villeAdapter;
    private List<Ville> villeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLocale(getSavedLocale());
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        villeList = new ArrayList<>();
        villeList.add(new Ville("alger", R.drawable.alger));
        villeList.add(new Ville("oran", R.drawable.oran));
        villeList.add(new Ville("tlemcen", R.drawable.tlemcen));
        villeList.add(new Ville("constantine", R.drawable.constantine));
        villeList.add(new Ville("ghardaia", R.drawable.ghardaia));
        villeList.add(new Ville("timimoun", R.drawable.timimoun));

        villeAdapter = new VilleAdapter(this, villeList, ville -> {
            Intent intent = new Intent(MainActivity.this, VilleActivity.class);
            intent.putExtra("ville_id", ville.getId());
            intent.putExtra("image_ville", ville.getImageResId());
            startActivity(intent);
        });

        recyclerView.setAdapter(villeAdapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);
    }

    private boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_settings) {
            showLanguageDialog();
            return true;
        } else if (item.getItemId() == R.id.nav_home) {
            Toast.makeText(this, getString(R.string.menu_home), Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.nav_favorites) {
            Toast.makeText(this, getString(R.string.menu_favorites), Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private void showLanguageDialog() {
        final String[] languages = {"Français", "العربية"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.choose_language));
        builder.setItems(languages, (dialog, which) -> {
            if (which == 0) {
                setLocale("fr");
                recreate();
            } else {
                setLocale("ar");
                recreate();
            }
        });
        builder.show();
    }

    private void setLocale(String langCode) {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getSharedPreferences("settings", MODE_PRIVATE).edit().putString("My_Lang", langCode).apply();
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }

    private String getSavedLocale() {
        return getSharedPreferences("settings", MODE_PRIVATE).getString("My_Lang", "fr");
    }
}
