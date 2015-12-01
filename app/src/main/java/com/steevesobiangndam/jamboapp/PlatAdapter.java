package com.steevesobiangndam.jamboapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Steeves Obiang Ndam on 25-11-15.
 */
public class PlatAdapter extends ArrayAdapter {
    private Context mContext;
    private int id;
    private ArrayList<HashMap<String, String>> items;
    private Activity activity;
    private FragmentActivity myContext;


    public PlatAdapter(Context context, int textViewResourceId, ArrayList<HashMap<String, String>> list, FragmentActivity c) {
        super(context, textViewResourceId, list);
        mContext = context;
        id = textViewResourceId;
        items = list;
        myContext = c;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View mView = convertView;
        if (mView == null) {
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = vi.inflate(id, null);
        }

            ImageView imagePlat = (ImageView) mView.findViewById(R.id.list_image);
            TextView nomCompte = (TextView) mView.findViewById(R.id.nom_plat);
            TextView prixPlat=(TextView) mView.findViewById(R.id.prix);

        //new thread !!!
        String uri = "@drawable/"+items.get(position).get("avatar");
        int imageResource = myContext.getResources().getIdentifier(uri, null,myContext.getPackageName());
        Drawable res = myContext.getResources().getDrawable(imageResource);
        imagePlat.setImageDrawable(res);

        //imagePlat.setImageResource(R.drawable.pastels);
        nomCompte.setText(items.get(position).get("nom"));
            prixPlat.setText(items.get(position).get("prix"));

            return mView;



    }
}