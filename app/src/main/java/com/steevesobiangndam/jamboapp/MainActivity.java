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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;
    ArrayAdapter<String> mForecastAdapter;
    private ArrayList<HashMap<String, String>> listHashVoyage = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ini_toolBar();
        ini_listView();

    }

    public void ini_toolBar(){
        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.tool_icon);
        getSupportActionBar().setTitle(Html.fromHtml("<center><font color='#ffffff'> Jambo</font></center>"));
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

        //listView.setAdapter(mForecastAdapter);

        JSONObject plat1 = new JSONObject();
        try {
            plat1.put("id", "1");
            plat1.put("nom", "Pastels");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONObject plat2 = new JSONObject();
        try {
            plat2.put("id", "2");
            plat2.put("nom", "Salade africaine");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONObject plat3 = new JSONObject();
        try {
            plat3.put("id", "3");
            plat3.put("nom", "Yassa");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONObject plat4 = new JSONObject();
        try {
            plat4.put("id", "4");
            plat4.put("nom", "Pundu");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONObject plat5 = new JSONObject();
        try {
            plat4.put("id", "5");
            plat4.put("nom", "Tieboudiem");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONObject plat6 = new JSONObject();
        try {
            plat4.put("id", "6");
            plat4.put("nom", "Bissap");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONObject plat7 = new JSONObject();
        try {
            plat7.put("id", "7");
            plat7.put("nom", "Gingembre");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONObject plat8 = new JSONObject();
        try {
            plat4.put("id", "8");
            plat4.put("nom", "Tiramisu saveurs exotiques");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        JSONArray jsonArrayPlats = new JSONArray();
        jsonArrayPlats.put(plat1);
        jsonArrayPlats.put(plat2);
        jsonArrayPlats.put(plat3);
        jsonArrayPlats.put(plat4);
        jsonArrayPlats.put(plat5);
        jsonArrayPlats.put(plat6);
        jsonArrayPlats.put(plat7);
        jsonArrayPlats.put(plat8);


        for (int i =0; i<jsonArrayPlats.length();i++){
            HashMap<String, String> hmap= new HashMap<String, String>();
            try {
                JSONObject json = jsonArrayPlats.getJSONObject(i);
                String id = json.getString("id");
                String nom= json.getString("nom");

                hmap.put("id",id);
                hmap.put("nom",nom);
                listHashVoyage.add(hmap);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        // rajouter les plats dans la hashMap pour ensuite les afficher avec la listView

        final ListView listView = (ListView) findViewById(R.id.listView_plats);
        PlatAdapter platAdapter = new PlatAdapter(this,R.layout.list_row,listHashVoyage,this);
        listView.setAdapter(platAdapter);
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
