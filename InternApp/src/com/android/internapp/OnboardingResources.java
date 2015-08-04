package com.android.internapp;
import java.util.ArrayList;

import com.parse.ParseUser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v4.app.ActionBarDrawerToggle;
//import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
public class OnboardingResources extends AppCompatActivity {


	private static String TAG = OnboardingResources.class.getSimpleName();

	ListView mDrawerList;
	RelativeLayout mDrawerPane;
	public ActionBarDrawerToggle mDrawerToggle;
	public DrawerLayout mDrawerLayout;

	ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_onboarding_resources);

		mNavItems.add(new NavItem("Profile", "", R.drawable.ic_launcher));
		mNavItems.add(new NavItem("User Search", "Other interns", R.drawable.ic_launcher));
		mNavItems.add(new NavItem("Maps", "Find GE locations", R.drawable.ic_launcher));
		mNavItems.add(new NavItem("FAQ", "Frequently asked questions", R.drawable.ic_launcher));
		mNavItems.add(new NavItem("Onboarding Resources", "For getting used to the place", R.drawable.ic_launcher));
		mNavItems.add(new NavItem("Logout", "Peace out homie", R.drawable.ic_launcher));


		// DrawerLayout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

		// drawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
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
				Log.d(TAG, "onDrawerClosed: " + getTitle());

				invalidateOptionsMenu();
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}
	@Override
	public void onBackPressed() {
	}
	/*
	 * Called when a particular item from the navigation drawer
	 * is selected.
	 * */
	public void selectItemFromDrawer(int position) {
		//	        Fragment fragment = new PreferencesFragment();
		//	     
		//	        FragmentManager fragmentManager = getFragmentManager();
		//	        fragmentManager.beginTransaction()
		//	                .replace(R.id.mainContent, fragment)
		//	                .commit();

		if (position==0) {
			Intent detailIntent1 = new Intent(this, Profile.class);
			detailIntent1.putExtra(ItemDetailFragment.ARG_ITEM_ID, position);
			startActivity(detailIntent1);
		} else if(position==1) {
			Intent detailIntent2 = new Intent(this, UserSearch.class);
			detailIntent2.putExtra(ItemDetailFragment.ARG_ITEM_ID, position);
			startActivity(detailIntent2);	
		} else if(position==2) {
			Intent detailIntent3 = new Intent(this, Map.class);
			detailIntent3.putExtra(ItemDetailFragment.ARG_ITEM_ID, position);
			startActivity(detailIntent3);	
		} else if(position==3) {
			Intent detailIntent4 = new Intent(this, FAQ.class);
			detailIntent4.putExtra(ItemDetailFragment.ARG_ITEM_ID, position);
			startActivity(detailIntent4);	
		}
		else if(position==4) {
			Intent detailIntent4 = new Intent(this, OnboardingResources.class);
			detailIntent4.putExtra(ItemDetailFragment.ARG_ITEM_ID, position);
			startActivity(detailIntent4);	
		}
		else if(position==5) {
			ParseUser.logOut();
			finish();
			quit();
//			android.os.Process.killProcess(android.os.Process.myPid());
//			super.onDestroy();
			
			

//			
////	        // Start and intent for the dispatch activity
//	        Intent intent = new Intent(this, LoginActivity.class);
//	        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//	        startActivity(intent);

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
	// Called when invalidateOptionsMenu() is invoked
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content view
		// boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerPane);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}
	public void quit() {
	    int pid = android.os.Process.myPid();
	    android.os.Process.killProcess(pid);
	    System.exit(0);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		//		if (id == R.id.action_settings) {
		//			return true;
		//		}

		// Pass the event to ActionBarDrawerToggle
		// If it returns true, then it has handled
		// the nav drawer indicator touch event
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);



	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
	    super.onPostCreate(savedInstanceState);
	    mDrawerToggle.syncState();
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
			// TextView subtitleView = (TextView) view.findViewById(R.id.subTitle);
			ImageView iconView = (ImageView) view.findViewById(R.id.icon);

			titleView.setText( mNavItems.get(position).mTitle );
			//   subtitleView.setText( mNavItems.get(position).mSubtitle );
			//    iconView.setImageResource(mNavItems.get(position).mIcon);

			return view;
		}
	}




}