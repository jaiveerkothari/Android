package com.android.internapp;
import java.util.ArrayList;
import android.app.Fragment;
//import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
//import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
public class OnboardingResources extends ActionBarActivity {
	
	
	private static String TAG = OnboardingResources.class.getSimpleName();
	 
	ListView mDrawerList;
	RelativeLayout mDrawerPane;
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawerLayout;
	 
	ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_onboarding_resources);
		
		
		 	mNavItems.add(new NavItem("User Search", "Other interns", R.drawable.ic_launcher));
		    mNavItems.add(new NavItem("Maps", "Find GE locations", R.drawable.ic_launcher));
		    mNavItems.add(new NavItem("FAQ", "Frequently asked questions", R.drawable.ic_launcher));
		    mNavItems.add(new NavItem("Onboarding Resources", "For getting used to the place", R.drawable.ic_launcher));
		    mNavItems.add(new NavItem("Settings", "Change settings", R.drawable.ic_launcher));
		    mNavItems.add(new NavItem("Logout", "Peace out homie", R.drawable.ic_launcher));
		 
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
		
		    
	}
	/*
	    * Called when a particular item from the navigation drawer
	    * is selected.
	    * */
	    private void selectItemFromDrawer(int position) {
//	        Fragment fragment = new PreferencesFragment();
//	     
//	        FragmentManager fragmentManager = getFragmentManager();
//	        fragmentManager.beginTransaction()
//	                .replace(R.id.mainContent, fragment)
//	                .commit();
	    	
	    	if (position==0) {
				Intent detailIntent1 = new Intent(this, UserSearch.class);
				detailIntent1.putExtra(ItemDetailFragment.ARG_ITEM_ID, position);
				startActivity(detailIntent1);
			} else if(position==1) {
				Intent detailIntent2 = new Intent(this, Map.class);
				detailIntent2.putExtra(ItemDetailFragment.ARG_ITEM_ID, position);
				startActivity(detailIntent2);	
			} else if(position==2) {
				Intent detailIntent3 = new Intent(this, FAQ.class);
				detailIntent3.putExtra(ItemDetailFragment.ARG_ITEM_ID, position);
				startActivity(detailIntent3);	
			} else if(position==3) {
				Intent detailIntent4 = new Intent(this, OnboardingResources.class);
				detailIntent4.putExtra(ItemDetailFragment.ARG_ITEM_ID, position);
				startActivity(detailIntent4);	
			}
	    	
	    	
			
	        mDrawerList.setItemChecked(position, true);
	        setTitle(mNavItems.get(position).mTitle);
	     
	        // Close the drawer
	        mDrawerLayout.closeDrawer(mDrawerPane);
	    }
	    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.onboarding_resources, menu);
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
	
	class NavItem {
	    String mTitle;
	    String mSubtitle;
	    int mIcon;
	 
	    public NavItem(String title, String subtitle, int icon) {
	        mTitle = title;
	        mSubtitle = subtitle;
	        mIcon = icon;
	    }
	}
	
	class DrawerListAdapter extends BaseAdapter {
		 
	    Context mContext;
	    ArrayList<NavItem> mNavItems;
	 
	    public DrawerListAdapter(Context context, ArrayList<NavItem> navItems) {
	        mContext = context;
	        mNavItems = navItems;
	    }
	 
	    @Override
	    public int getCount() {
	        return mNavItems.size();
	    }
	 
	    @Override
	    public Object getItem(int position) {
	        return mNavItems.get(position);
	    }
	 
	    @Override
	    public long getItemId(int position) {
	        return 0;
	    }
	 
	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        View view;
	 
	        if (convertView == null) {
	            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            view = inflater.inflate(R.layout.drawer_item, null);
	        }
	        else {
	            view = convertView;
	        }
	 
	        TextView titleView = (TextView) view.findViewById(R.id.title);
	        TextView subtitleView = (TextView) view.findViewById(R.id.subTitle);
	        ImageView iconView = (ImageView) view.findViewById(R.id.icon);
	 
	        titleView.setText( mNavItems.get(position).mTitle );
	        subtitleView.setText( mNavItems.get(position).mSubtitle );
	        iconView.setImageResource(mNavItems.get(position).mIcon);
	 
	        return view;
	    }
	}
	
	
	
	
}