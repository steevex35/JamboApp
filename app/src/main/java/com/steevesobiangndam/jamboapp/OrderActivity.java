package com.steevesobiangndam.jamboapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ArrayList<HashMap<String, String>> listHashPlats = new ArrayList<HashMap<String, String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ini_toolBar();
        ini_listView();
    }

    public void ini_toolBar(){
        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setLogo(R.drawable.tool_icon);
        getSupportActionBar().setTitle(Html.fromHtml("<center><font color='#ffffff'> Mon Pagnier </font></center>"));
    }

    public void ini_listView() {

        JSONObject plat1 = new JSONObject();
        try {
            plat1.put("id", "1");
            plat1.put("nom", "Pastels");
            plat1.put("prix","15€");
            plat1.put("rate","4");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONObject plat2 = new JSONObject();
        try {
            plat2.put("id", "2");
            plat2.put("nom", "Salade africaine");
            plat2.put("prix","8€");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONObject plat3 = new JSONObject();
        try {
            plat3.put("id", "3");
            plat3.put("nom", "Yassa");
            plat3.put("prix","14€");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONObject plat4 = new JSONObject();
        try {
            plat4.put("id", "4");
            plat4.put("nom", "Pundu");
            plat1.put("prix","15€");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONObject plat5 = new JSONObject();
        try {
            plat5.put("id", "5");
            plat5.put("nom", "Tieboudiem");
            plat5.put("prix","9€");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONObject plat6 = new JSONObject();
        try {
            plat6.put("id", "6");
            plat6.put("nom", "Bissap");
            plat6.put("prix","9€");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONObject plat7 = new JSONObject();
        try {
            plat7.put("id", "7");
            plat7.put("nom", "Gingembre");
            plat7.put("prix","2€");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONObject plat8 = new JSONObject();
        try {
            plat8.put("id", "8");
            plat8.put("nom", "Tiramisu saveurs exotiques");
            plat8.put("prix","5€");
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


        for (int i = 0; i < jsonArrayPlats.length(); i++) {
            HashMap<String, String> hmap = new HashMap<String, String>();
            try {
                JSONObject json = jsonArrayPlats.getJSONObject(i);
                String id = json.getString("id");
                String nom = json.getString("nom");
                String prix= json.getString("prix");

                hmap.put("id", id);
                hmap.put("nom", nom);
                hmap.put("prix",prix);
                listHashPlats.add(hmap);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        final ListView listView = (ListView) findViewById(R.id.commande_list);
        PlatAdapter platAdapter = new PlatAdapter(this, R.layout.list_row_order, listHashPlats, this);
        listView.setAdapter(platAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                TextView nom = (TextView) view.findViewById(R.id.nom_plat);

                String nomToSend = nom.getText().toString();
                //Toast toast = Toast.makeText(getApplication(), forecast, Toast.LENGTH_SHORT);
                //toast.show();
                Intent intent = new Intent(getApplication(), DetailActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, nomToSend);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order, menu);
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
