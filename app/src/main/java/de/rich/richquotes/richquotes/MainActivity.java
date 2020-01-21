package de.rich.richquotes.richquotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button allQuotes;
    Button favouriteQuotes;
    SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();
        this.sharedPref = context.getSharedPreferences(
                "MyPref", Context.MODE_PRIVATE);

        allQuotes = findViewById(R.id.all_quotes);
        favouriteQuotes = findViewById(R.id.favourite_quotes);

        allQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToAllQuotes = new Intent(MainActivity.this, AllQuotesActivity.class);
                startActivity(intentToAllQuotes);
            }
        });

        favouriteQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    Intent intentToFavouriteQuotes = new Intent(MainActivity.this, FavouriteQuotesActivity.class);
                    startActivity(intentToFavouriteQuotes);



            }
        });
    }
}
