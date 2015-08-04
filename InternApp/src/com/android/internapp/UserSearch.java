package com.android.internapp;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;


public class UserSearch extends OnboardingResources {
	TextView selection;
	protected EditText sUsername; 
	ArrayList<String> userIDs;
	ArrayAdapter<String> listAdapter;
	@Override
	public void onStart() {
		super.onStart();

	}
	@Override
	public void onBackPressed() {
	}
	public void getFriends(List<String> numbers) {
		userIDs = new ArrayList<String>();
		List<ParseQuery<ParseUser>> queries = new ArrayList<ParseQuery<ParseUser>>();
		for (String number : numbers) {
			ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
			queries.add(parseQuery);
		}

		ParseQuery<ParseUser> userQuery = ParseQuery.or(queries);

		userQuery.findInBackground(new FindCallback<ParseUser>() {

			@Override
			public void done(List<ParseUser> numberList, ParseException e) {
				if (e == null) {
					listAdapter.clear();
					userIDs.clear();
					for (int i = 0; i < numberList.size(); i++) {
						// What to do here?
								ParseUser user = numberList.get(i);
						String firstname = user.getString("firstName");
						String lastname = user.getString("lastName");
						String college = user.getString("college");
						String major = user.getString("major");
						String email = user.getString("email");
						String objectid = user.getObjectId();
						//	                	if (i == 1) {
						//	                	ParseFile image = (ParseFile) user.get("profilePicture");
						//	                	byte[] data;
						//	                	try {
						//	                		data = image.getData();
						//	                		Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
						//		                    ImageView pic;
						//	//
						//		                    pic = (ImageView) findViewById(R.id.profilepic);
						//		                    pic.setImageBitmap(bmp);
						//						} catch (ParseException e1) {
						//							data = null;
						//							// TODO Auto-generated catch block
						//							e1.printStackTrace();
						//						}
						//	                	}
						boolean checkSearch = false;
						String searchText = sUsername.getText().toString().toLowerCase();
						if (firstname.toLowerCase().contains(searchText)) {
							checkSearch = true;
						}
						if (lastname.toLowerCase().contains(searchText)) {
							checkSearch = true;
						}
						if (college.toLowerCase().contains(searchText)) {
							checkSearch = true;
						}
						if (major.toLowerCase().contains(searchText)) {
							checkSearch = true;
						}
						if (email.toLowerCase().contains(searchText)) {
							checkSearch = true;
						}
						if (checkSearch) {
							listAdapter.insert(firstname + " " + lastname, 0);
							userIDs.add(0,objectid);
						}


						//Use this list of ParseUser (numberList) in an ArrayAdapter
						//or save it in a database
					}
					listAdapter.notifyDataSetChanged();
				}
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_search);
		
		
		
		
		
		
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
		

		selection = (TextView) findViewById(R.id.selection);
		sUsername = (EditText)findViewById(R.id.searchFriend);
		// Set up the listview
		ArrayList<String> playerlist = new ArrayList<String>();
		// Create and populate an ArrayList of objects from parse
		listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
		final ListView playerlv = (ListView)findViewById(android.R.id.list);
		playerlv.setAdapter(listAdapter);



		sUsername.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {
				List<String> numbers = new ArrayList<String>();
				numbers.add("3");
				numbers.add("4");
				getFriends(numbers);
			}

			public void beforeTextChanged(CharSequence s, int start, 
					int count, int after) {
				List<String> numbers2 = new ArrayList<String>();
				numbers2.add("3");
				numbers2.add("4");
				getFriends(numbers2);
			}

			public void onTextChanged(CharSequence s, int start, 
					int before, int count) {

			}
		});

		//getFriends(numbers);
		//listAdapter.notifyDataSetChanged();
		//listAdapter.add("name");
		//listAdapter.notifyDataSetChanged();
		final Intent detailIntent4 = new Intent(this, ViewProfile.class);
		playerlv.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int position,
					long arg3) 
			{
				String value = (String)adapter.getItemAtPosition(position); 
				String id = userIDs.get(position);
				// assuming string and if you want to get the value on click of list item
				// do what you intend to do on click of listview row
				detailIntent4.putExtra("name", id);
				v.getContext().startActivity(detailIntent4);
			}

		});
		
		
		

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_profile, menu);
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
	
	
}

