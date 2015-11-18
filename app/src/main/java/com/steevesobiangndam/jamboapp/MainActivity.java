package com.steevesobiangndam.jamboapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.steevesobiangndam.jamboapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;
    ArrayAdapter<String> mForecastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ini_toolBar();
        ini_listView();

    }

    public void ini_toolBar(){
        ActionBar actionBar = getSupportActionBar();
        //actionBar.setLogo(R.drawable.logoJambo);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#A1887F")));
        //actionBar.setDisplayShowHomeEnabled(true);
    }

    /**
     * TO-DO mettre la listView dans un thread
     */

    public void ini_listView(){
        String[] ArrayPlats ={
                "Poulet yassa -- €€",
                "Jambo Box --  €€",
                "Jambox BeatBox -- €€",
                "Jambo 2   -- €€",
                "Jambo 3   -- €€",
                "Jambo 4   -- €€",
                "Jambo 5   -- €€"
        };
        List<String> listPlats = new ArrayList<String>(Arrays.asList(ArrayPlats));
        mForecastAdapter =
                new ArrayAdapter<String>(
                        //Current Context(Fragment parent activity
                        this,
                        //ID of list item layout
                        R.layout.list_item_plats,
                        //ID of the textview to populate
                        R.id.list_item_plats_textview,
                        //Forecast data
                        listPlats);
        ListView listView = (ListView) findViewById(R.id.listView_plats);
        listView.setAdapter(mForecastAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String forecast = mForecastAdapter.getItem(position);
                //Toast toast = Toast.makeText(getApplication(), forecast, Toast.LENGTH_SHORT);
                //toast.show();
                Intent intent = new Intent(getApplication(),DetailActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, forecast);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
