package com.example.hopsguide;
 
import java.util.ArrayList;
 
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
 
public class HopDescActivity extends Activity {
 
        TextView name;
        TextView nameDesc;
        TextView country;
        TextView countryDesc;
        TextView alpha;
        TextView alphaDesc;
        TextView beta;
        TextView betaDesc;
        TextView type;
        TextView typeDesc;
        TextView storageIndex;
        TextView storageIndexDesc;
        TextView typicalFor;
        TextView typicalForDesc;
        TextView substitutes;
        TextView substitutesDesc;
        TextView aroma;
        TextView aromaDesc;
        TextView information;
        TextView informationDesc;
 
        Hops hop;
        ArrayList AllTextViews;
 
        ArrayList<TextView> allTextViews = new ArrayList<TextView>();
 
        public void onCreate(Bundle savedInstanceState) {
 
                super.onCreate(savedInstanceState);
                setContentView(R.layout.hopsdescription);
 
                name = (TextView) findViewById(R.id.textView1);
                allTextViews.add(name);
                nameDesc = (TextView) findViewById(R.id.textView2);
                allTextViews.add(nameDesc);
                country = (TextView) findViewById(R.id.textView3);
                allTextViews.add(country);
                countryDesc = (TextView) findViewById(R.id.textView4);
                allTextViews.add(countryDesc);
                alpha = (TextView) findViewById(R.id.textView5);
                allTextViews.add(alpha);
                alphaDesc = (TextView) findViewById(R.id.textView6);
                allTextViews.add(alphaDesc);
                beta = (TextView) findViewById(R.id.textView7);
                allTextViews.add(beta);
                betaDesc = (TextView) findViewById(R.id.textView8);
                allTextViews.add(betaDesc);
                type = (TextView) findViewById(R.id.textView9);
                allTextViews.add(type);
                typeDesc = (TextView) findViewById(R.id.textView10);
                allTextViews.add(typeDesc);
                storageIndex = (TextView) findViewById(R.id.textView11);
                allTextViews.add(storageIndex);
                storageIndexDesc = (TextView) findViewById(R.id.textView12);
                allTextViews.add(storageIndexDesc);
                typicalFor = (TextView) findViewById(R.id.textView13);
                allTextViews.add(typicalFor);
                typicalForDesc = (TextView) findViewById(R.id.textView14);
                allTextViews.add(typicalForDesc);
                substitutes = (TextView) findViewById(R.id.textView15);
                allTextViews.add(substitutes);
                substitutesDesc = (TextView) findViewById(R.id.textView16);
                allTextViews.add(substitutesDesc);
                aroma = (TextView) findViewById(R.id.textView17);
                allTextViews.add(aroma);
                aromaDesc = (TextView) findViewById(R.id.textView18);
                allTextViews.add(aromaDesc);
                information = (TextView) findViewById(R.id.textView19);
                allTextViews.add(information);
                informationDesc = (TextView) findViewById(R.id.textView20);
                allTextViews.add(informationDesc);
               
                Intent intent = getIntent();
                String hopsName = intent.getStringExtra("Hopsname");
               
                setTitle("Hop Description - " + hopsName);
                hop = SearchActivity.getHops(hopsName);
 
                name.setText("Name");
                nameDesc.setText(hopsName);
                country.setText("Country");
                countryDesc.setText(hop.getCountry());
                alpha.setText("Alpha");
                alphaDesc.setText("" + hop.getAlpha());
                beta.setText("Beta");
                betaDesc.setText("" + hop.getBeta());
                type.setText("Type");
                typeDesc.setText(hop.getType());
                storageIndex.setText("Storage Index");
                storageIndexDesc.setText("" + hop.getStorageIndex());
                typicalFor.setText("Typical For");
                typicalForDesc.setText(hop.getTypicalFor());
                substitutes.setText("Substitutes");
                substitutesDesc.setText(hop.getSubstitutes());
                aroma.setText("Aroma");
                aromaDesc.setText(hop.getAroma());
                information.setText("Information");
                informationDesc.setText(hop.getInformation());
 
                Typeface typeface = Typeface.createFromAsset(getAssets(),
                                "fonts/MyriadPro_Regular.ttf");
                for (View view : allTextViews) {
                        if (view instanceof TextView) {
                                TextView textView = (TextView) view;
                                textView.setTypeface(typeface);
                                textView.setTextColor(Color.GRAY);
                        }
                }
 
        }
 
}