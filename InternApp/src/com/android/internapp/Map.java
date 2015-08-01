package com.android.internapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Map extends OnboardingResources
{
	ListView lv;
    TextView selection;
    ListItem[] items = { 
            new ListItem("Blue Ash Tech Center", BATC.class), 
            new ListItem("BEBC", BBC.class), 
            new ListItem("Building 800 (Orientation)", Bldg800.class), 
            new ListItem("Crown Point", CrownPoint.class), 
            new ListItem("Evendale Plant", Evendale.class), 
            new ListItem("Indiana Wesleyan University", IWU.class), 
            new ListItem("North Pointe 1", NP1.class), 
            new ListItem("North Pointe 2", NP2.class), 
            new ListItem("Princeton Hill (CTEC)", PrincetonHill.class), 
            new ListItem("Quality Technology Center", QTC.class)};

    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        setContentView(R.layout.activity_map);
        
        // DrawerLayout
	    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
	 
	    // Populate the Navigtion Drawer with options
	    mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
	    mDrawerList = (ListView) findViewById(R.id.navList);
	    DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
	    mDrawerList.setAdapter(adapter);
	 
	    // Drawer Item click listeners
	    mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	            selectItemFromDrawer(position);
	        }
	    });
	    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	    mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
	        @Override
	        public void onDrawerOpened(View drawerView) {
	            super.onDrawerOpened(drawerView);
	     
	            invalidateOptionsMenu();
	        }
	     
	        @Override
	        public void onDrawerClosed(View drawerView) {
	            super.onDrawerClosed(drawerView);
	           // Log.d(TAG, "onDrawerClosed: " + getTitle());
	     
	            invalidateOptionsMenu();
	        }
	    };
	     
	    mDrawerLayout.setDrawerListener(mDrawerToggle);
        
        
        
        lv = (ListView)findViewById(android.R.id.list);
        ArrayAdapter<ListItem> adapter2 = new ArrayAdapter<ListItem>(this, android.R.layout.simple_list_item_1, items);
        
        lv.setAdapter(adapter2);
//        setListAdapter(new ArrayAdapter<ListItem>(
//                this, android.R.layout.simple_list_item_1, items));
        selection = (TextView) findViewById(R.id.selection);
        lv.setBackgroundColor(Color.BLACK);
        final Intent intent = new Intent(this, BATC.class);
        final Intent intent1 = new Intent(this, BBC.class);
        final Intent intent2 = new Intent(this, Bldg800.class);
        final Intent intent3 = new Intent(this, CrownPoint.class);
        final Intent intent4 = new Intent(this, Evendale.class);
        final Intent intent5 = new Intent(this, IWU.class);
        final Intent intent6 = new Intent(this, NP1.class);
        final Intent intent7 = new Intent(this, NP2.class);
        final Intent intent8 = new Intent(this, PrincetonHill.class);
        final Intent intent9 = new Intent(this, QTC.class);
//      startActivityForResult(intent, position);
        
        lv.setOnItemClickListener(new OnItemClickListener()
        {
             @Override
             public void onItemClick(AdapterView<?> a, View v,int position, long id) 
             {
            	 Toast.makeText(getBaseContext(), items[position].getLabel(), Toast.LENGTH_LONG).show();
                 switch (position)
                 {
                     case 0:
                    	 startActivityForResult(intent, position);
                         break;
                     case 1:
                    	 startActivityForResult(intent1, position);
                         break;
                     case 2:
                    	 startActivityForResult(intent2, position);
                         break;
                     case 3:
                    	 startActivityForResult(intent3, position);
                         break;
                     case 4:
                    	 startActivityForResult(intent4, position);
                         break;
                     case 5:
                    	 startActivityForResult(intent5, position);
                         break;
                     case 6:
                    	 startActivityForResult(intent6, position);
                         break;
                     case 7:
                    	 startActivityForResult(intent7, position);
                         break;
                     case 8:
                    	 startActivityForResult(intent8, position);
                         break;
                     case 9:
                    	 startActivityForResult(intent9, position);
                         break;
                     default:
                         break;
                 }
//                 final Intent intent = new Intent(this, items[position].getActivity());
              }
        });
    }

//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id)
//    {
//        super.onListItemClick(l, v, position, id);
//        final Intent intent = new Intent(this, items[position].getActivity());
//        startActivityForResult(intent, position);
//    }
    
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
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