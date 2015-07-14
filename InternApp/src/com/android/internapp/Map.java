package com.android.internapp;

import android.support.v7.app.ActionBarActivity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class Map extends ListActivity
{
    TextView selection;
    ListItem[] items = { 
            new ListItem("Blue Ash Tech Center", BATC.class), 
            new ListItem("BBC", BBC.class), 
            new ListItem("Building 800 (Orientation)", Bldg800.class), 
            new ListItem("Crown Point", CrownPoint.class), 
            new ListItem("Evendale Plant", Evendale.class), 
            new ListItem("Indiana Wesleyan University", IWU.class), 
            new ListItem("North Pointe 1", NP1.class), 
            new ListItem("North Pointe 2", NP2.class), 
            new ListItem("Princeton Hill", PrincetonHill.class), 
            new ListItem("Quality Technology Center", QTC.class)};

    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        setContentView(R.layout.activity_map);
        setListAdapter(new ArrayAdapter<ListItem>(
                this, android.R.layout.simple_list_item_1, items));
        selection = (TextView) findViewById(R.id.selection);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);
        final Intent intent = new Intent(this, items[position].getActivity());
        startActivityForResult(intent, position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK)
        {
            // Perform different actions based on from which activity is
            // the application returning:
            switch (requestCode)
            {
                case 0:
                    // TODO: handle the return of the StartTripActivity
                    break;
                case 1:
                    // TODO: handle the return of the ClockinActivity
                    break;
                case 2:
                    // TODO: handle the return of the CustomerSvcActivity
                    // and so on...
                    break;
                default:
                    break;
            }
        }
        else if (resultCode == RESULT_CANCELED)
        {
           // resultsTxt.setText("Canceled");
        }
    }
}