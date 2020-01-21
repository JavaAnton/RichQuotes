package de.rich.richquotes.richquotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FavouriteQuotesActivity extends AppCompatActivity {

    SharedPreferences sharedPref;
    Editor editor;
    Button button;
    Button button2;
    Button button3;
    Button button4;
    TextView tv;
    TextView tv2;
    int counter;
    int maximum;
    List<String> list;
   // HashMap map = new HashMap();
    int addDifference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.favourite_quotes);

        list = new ArrayList<>();
        Context context = getApplicationContext();
        this.sharedPref = context.getSharedPreferences(
                "MyPref", Context.MODE_PRIVATE);

        maximum = sharedPref.getInt("listSize", 0);

        for (int i = 0; i < maximum; i++) {
            list.add(sharedPref.getString("item" + i, null));
           // map.put(i, sharedPref.getString("item" + i, null));
        }
        editor = sharedPref.edit();

        counter = 0;
        tv = findViewById(R.id.textViewXXX);
        tv2 = findViewById(R.id.textView3XXX);
        button = findViewById(R.id.btnOneXXX);
        button2 = findViewById(R.id.btnTwoXXX);
        button3 = findViewById(R.id.btnThreeXXX);
        button4 = findViewById(R.id.btnFourXXX);

        tv.setText(list.get(0).toString());
        tv2.setText(list.get(1).toString());


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter == (maximum - 2)) {
                    counter = -2;
                }
                counter = counter + 2;
                tv.setText(list.get(counter).toString());
                tv2.setText(list.get(counter + 1).toString());

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter == 0) {
                    counter = maximum;
                }
                counter = counter - 2;
                tv.setText(list.get(counter).toString());
                tv2.setText(list.get(counter + 1).toString());

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                list.remove(counter);
                list.remove(counter);
                maximum = list.size();
                editor.putInt("listSize", maximum);

                if (maximum == 0) {
                    finish();
                } else if (maximum == counter) {
                    counter = 0;
                    tv.setText(list.get(counter).toString());
                    tv2.setText(list.get(counter + 1).toString());
                } else {
                    tv.setText(list.get(counter).toString());
                    tv2.setText(list.get(counter + 1).toString());
                }


              /*  for (Map.Entry mapElement : hm.entrySet()) {
                    String key = (String) mapElement.getKey();


                    // Add some bonus marks
                    // to all the students and print it
                    int value = ((int)mapElement.getValue() + 10);

                    System.out.println(key + " : " + value);
                }*/


            }
        });

    }
}
