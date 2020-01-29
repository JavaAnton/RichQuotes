package de.rich.richquotes.richquotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class AllQuotesActivity extends AppCompatActivity {

    Button button;
    Button button2;
    Button button3;
    Button button4;
    TextView tv;
    TextView tv2;
    int counter;
    SharedPreferences sharedPref;
    ArrayList<String> list = new ArrayList<>();
    String[] quotesAndAuthors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.all_quotes);

        Resources res = getResources();
        Context context = getApplicationContext();
        this.sharedPref = context.getSharedPreferences(
                "MyPref", Context.MODE_PRIVATE);
        quotesAndAuthors = res.getStringArray(R.array.quotes);

        counter = 0;
        Intent intent = getIntent();
        button = findViewById(R.id.btnOne);
        button2 = findViewById(R.id.btnTwo);
        button3 = findViewById(R.id.btnThree);
        button4 = findViewById(R.id.btnFour);
        tv = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView3);
        tv.setText(quotesAndAuthors[counter]);
        tv2.setText(quotesAndAuthors[counter + 1]);


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter == 42) {
                    counter = -2;
                }
                counter = counter + 2;
                tv.setText(quotesAndAuthors[counter]);
                tv2.setText(quotesAndAuthors[counter + 1]);

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter == 0) {
                    counter = 44;
                }
                counter = counter - 2;
                tv.setText(quotesAndAuthors[counter]);
                tv2.setText(quotesAndAuthors[counter + 1]);

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   list.add(quotesAndAuthors[counter]);
             //   list.add(quotesAndAuthors[counter + 1]);

                sharedPref.edit().putBoolean("bool0", true).apply();
                sharedPref.edit().remove("bool1").apply();
                sharedPref.edit().putBoolean("bool1", true).apply();
               // sharedPref.edit().commit();
                Log.v("TEST" ,"" + sharedPref.getBoolean("bool1", true));



                tv.setText(quotesAndAuthors[counter]);
                tv2.setText(quotesAndAuthors[counter + 1]);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("RichQuotes", "Its paused mkay");

        Editor editor = sharedPref.edit();

        editor.putInt("listSize", this.list.size());

        for(int i = 0; i < quotesAndAuthors.length; i++)
        {
         //   editor.remove("item" + i); // macht irgendwas seltsames, werte werden nicht wirklich gelöscht
            editor.putString("item" + i, quotesAndAuthors[i]);
        }

        for(int i = 0; i < quotesAndAuthors.length; i++)
        {
            if (sharedPref.getBoolean("bool" + i, false) == false) {
                Log.v("itsfalse", "Boolean " + i + "is false!");
                editor.putBoolean("bool" + i, false).apply();
            } else {
                Log.v("itstrue", "Boolean " + i + "is true!");
                editor.putBoolean("bool" + i, true).apply();
            }
        }

        //editor.putBoolean("bool0", true);

        for(int i = 0; i < quotesAndAuthors.length; i++)
        {
            Log.v("nibba ", "y " + sharedPref.getString("item" + i, null));
            Log.v("boolArray index " + i, Boolean.toString(sharedPref.getBoolean("bool" + i, false)));
        }

        //editor.commit();
       // editor.apply();
    }

}
