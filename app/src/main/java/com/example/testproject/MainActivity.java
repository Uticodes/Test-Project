package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView ;
    List<Persons> people = new ArrayList<>();

    // Current count
    private int mCount = 0;
    // Current background color
    private int mColor;
    // Text view to display both count and color
    private TextView mShowCountTextView;

    // Key for current count
    private final String COUNT_KEY = "count";
    // Key for current color
    private final String COLOR_KEY = "color";
    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.example.testproject";

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        // Restore preferences
        mCount = mPreferences.getInt(COUNT_KEY, 0);
        mShowCountTextView.setText(String.format("%s", mCount));
        mColor = mPreferences.getInt(COLOR_KEY, mColor);
        mShowCountTextView.setBackgroundColor(mColor);

       /* mRecyclerView = findViewById(R.id.recycler);

        String[] names = {
                "Janet","Utibe","Ada","Mark","Okon","Samfield","Melody","Felicia",
                "Fabulous","Sherg","Bassey","Nsikak", "Tom", "Vincent",
                "Loverth","Sherg","Fortune","Nsikak", "Gate", "Linus",
                "Agnes","John","Fortune","James", "Tom", "Jane",
                "Bassey","Gabriel","Fortune","Nsikak", "Tom", "Vincent",
                "Moses","Sherg","Bright","Margrete", "Tom", "Weeks",
                "Fabulous","Sherg","Kate","Maggi", "Tom", "Lui",
        };

        for(int np =0; np < names.length; np++){

            Persons person = new Persons();
            person.setName(names[np]);
            person.setAge((int) (Math.random() * 49 + 1));
            //person.setImage(getDrawable(R.drawable.ic_android_black_24dp));
            person.setHeight((Math.random() * 49 + 1)/2);
            if(np %2 ==0){
                person.setGender("female");
            }else {
                person.setGender("male");
            }
            people.add(person);
        }*/

       /* RecyclerAdapter myAdapter = new RecyclerAdapter(this,people);

        mRecyclerView.setAdapter(myAdapter);

        //Help position the recycler horizontally
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration decoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(decoration);*/

        // Initialize views, color
        mShowCountTextView = findViewById(R.id.count_textview);
        mColor = ContextCompat.getColor(this,
                R.color.default_background);

        // Restore the saved instance state.
        if (savedInstanceState != null) {

            mCount = savedInstanceState.getInt(COUNT_KEY);
            if (mCount != 0) {
                mShowCountTextView.setText(String.format("%s", mCount));
            }

            mColor = savedInstanceState.getInt(COLOR_KEY);
            mShowCountTextView.setBackgroundColor(mColor);
        }

    }
    /**
     * Handles the onClick for the background color buttons. Gets background
     * color of the button that was clicked, and sets the TextView background
     * to that color.
     *
     * @param view The view (Button) that was clicked.
     */
    public void changeBackground(View view) {
        int color = ((ColorDrawable) view.getBackground()).getColor();
        mShowCountTextView.setBackgroundColor(color);
        mColor = color;
    }

    /**
     * Handles the onClick for the Count button. Increments the value of the
     * mCount global and updates the TextView.
     *
     * @param view The view (Button) that was clicked.
     */
    public void countUp(View view) {
        mCount++;
        mShowCountTextView.setText(String.format("%s", mCount));
    }

    /**
     * Saves the instance state if the activity is restarted (for example,
     * on device rotation.) Here you save the values for the count and the
     * background color.
     *
     * @param outState The state data.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(COUNT_KEY, mCount);
        outState.putInt(COLOR_KEY, mColor);
    }

    /**
     * Handles the onClick for the Reset button. Resets the global count and
     * background variables to the defaults and resets the views to those
     * default values.
     *
     * @param view The view (Button) that was clicked.
     */
    public void reset(View view) {
        // Reset count
        mCount = 0;
        mShowCountTextView.setText(String.format("%s", mCount));

        // Reset color
        mColor = ContextCompat.getColor(this,
                R.color.default_background);
        mShowCountTextView.setBackgroundColor(mColor);

        // Clear preferences
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();
    }

    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(COUNT_KEY, mCount);
        preferencesEditor.putInt(COLOR_KEY, mColor);
        preferencesEditor.apply();
        // ...
    }

}
