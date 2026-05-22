package com.thanuboard.app;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout btnThemes = findViewById(R.id.btnThemes);
        LinearLayout btnEmoji = findViewById(R.id.btnEmoji);
        LinearLayout btnFonts = findViewById(R.id.btnFonts);
        LinearLayout btnSettings = findViewById(R.id.btnSettings);

        btnThemes.setOnClickListener(v -> Toast.makeText(this, "Themes coming soon!", Toast.LENGTH_SHORT).show());
        btnEmoji.setOnClickListener(v -> Toast.makeText(this, "Emoji Store coming soon!", Toast.LENGTH_SHORT).show());
        btnFonts.setOnClickListener(v -> Toast.makeText(this, "Font Store coming soon!", Toast.LENGTH_SHORT).show());
        
        // Settings බටන් එක එබුවාම Keyboard එක Active කරන්න Shortcut එකක් ලෙස ක්‍රියා කරයි
        btnSettings.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "Go to Phone Settings to enable ThanuBoard", Toast.LENGTH_LONG).show();
            }
        });
    }
}
